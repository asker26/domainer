package main

import (
	"bufio"
	"fmt"
	"io"
	"log"
	"os"
	"os/exec"
	"path/filepath"
	"strings"
)

func main() {

	if len(os.Args) != 2 {
		log.Fatal("Please provide entity like `domainer <entity>` ")
		return
	}

	// Find PHP path
	phpPath := findPHP()
	if phpPath == "" {
		log.Fatal("PHP is not found in Laragon or system PATH. Please make sure PHP is properly installed.")
		return
	}

	// Check for required commands
	if !commandExists("composer") {
		log.Fatal("Composer is not installed or not in PATH. Please install Composer and make sure it's available in your system PATH")
		return
	}

	entity := os.Args[1]

	addonTitle, err := scanFor("AddonTitle", "", nil)
	if err != nil {
		log.Fatal(err)
		return
	}
	if addonTitle == "" {
		log.Fatal("AddonTitle is required")
		return
	}

	addonDescription, err := scanFor("AddonDescription", "", nil)
	if err != nil {
		log.Fatal(err)
		return
	}
	if addonDescription == "" {
		log.Fatal("AddonDescription is required")
		return
	}

	slugPascalCase := toPascalCase(entity)

	fmt.Printf("Creating entity \"%s\" with title \"%s\", description \"%s\"...\n", entity, addonTitle, addonDescription)

	var domain = struct {
		Entity           string
		AddonTitle       string
		AddonDescription string
		SlugPascalCase   string
	}{
		Entity:           entity,
		AddonTitle:       addonTitle,
		AddonDescription: addonDescription,
		SlugPascalCase:   slugPascalCase,
	}

	// Create destination directory based on the entity name
	currentDir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
		return
	}

	domainDst := filepath.Join(currentDir, fmt.Sprintf("booknetic-%s", strings.ToLower(domain.Entity)))

	err = os.MkdirAll(domainDst, os.ModePerm)
	if err != nil {
		log.Fatal(err)
		return
	}

	err = copyDir(getTemplatePath("booknetic-{slug}"), domainDst)
	if err != nil {
		log.Fatal(err)
		return
	}

	err = replacePlaceholders(domainDst, domain)
	if err != nil {
		log.Fatal(err)
		return
	}

	// Run composer install in the generated plugin directory
	fmt.Println("Running composer install...")
	composerPath, err := exec.LookPath("composer")
	if err != nil {
		log.Fatal(err)
		return
	}
	cmd := exec.Command(composerPath, "install")
	cmd.Dir = domainDst
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	// Set composer process environment to include PHP path
	cmd.Env = append(os.Environ(),
		fmt.Sprintf("PATH=%s;%s", filepath.Dir(phpPath), os.Getenv("PATH")),
		fmt.Sprintf("PHP_BINARY=%s", phpPath),
	)

	err = cmd.Run()
	if err != nil {
		log.Printf("Failed to run composer install: %v", err)
		log.Printf("Using PHP from: %s", phpPath)
		log.Printf("Using Composer from: %s", composerPath)
		log.Fatal(err)
		return
	}

	fmt.Println("Plugin generated successfully!")

}

func scanFor(key string, defaultValue string, whiteList []string) (myVal string, err error) {
	reader := bufio.NewReader(os.Stdin)

	if defaultValue != "" {
		fmt.Printf("Enter your %s (Default: %s): ", key, defaultValue)
	} else {
		fmt.Printf("Enter your %s: ", key)
	}

	myVal, err = reader.ReadString('\n')
	if err != nil {
		if err == io.EOF {
			return defaultValue, nil
		}
		return "", err
	}

	myVal = strings.TrimSpace(myVal)

	if len(myVal) == 0 {
		return defaultValue, nil
	}

	if whiteList != nil && !contains(whiteList, myVal) {
		fmt.Printf("invalid %s. Allowed %ss are: %s\n", key, key, strings.Join(whiteList, ", "))
		return scanFor(key, defaultValue, whiteList)
	}

	return myVal, nil
}

func getTemplatePath(template string) string {
	// Get the executable path
	ex, err := os.Executable()
	if err != nil {
		log.Fatal(err)
	}

	// Get the directory containing the executable
	exPath := filepath.Dir(ex)

	// Templates should be in the same directory as the executable
	return filepath.Join(exPath, "templates", template)
}

func copyDir(src string, dst string) error {
	var err error
	var fds []os.FileInfo
	var srcfd *os.File

	if srcfd, err = os.Open(src); err != nil {
		return err
	}
	defer srcfd.Close()

	if fds, err = srcfd.Readdir(0); err != nil {
		return err
	}
	if err = os.MkdirAll(dst, os.ModePerm); err != nil {
		return err
	}

	for _, fd := range fds {
		var srcfp string
		var dstfp string
		var err error
		var fdsrc *os.File
		var fddst *os.File

		srcfp = filepath.Join(src, fd.Name())
		dstfp = filepath.Join(dst, fd.Name())

		if fd.IsDir() {
			if err = copyDir(srcfp, dstfp); err != nil {
				return err
			}
		} else {
			if fdsrc, err = os.Open(srcfp); err != nil {
				return err
			}
			defer fdsrc.Close()

			if fddst, err = os.Create(dstfp); err != nil {
				return err
			}
			defer fddst.Close()

			if _, err = io.Copy(fddst, fdsrc); err != nil {
				return err
			}
		}
	}

	return nil
}

func replacePlaceholders(dst string, domain struct {
	Entity           string
	AddonTitle       string
	AddonDescription string
	SlugPascalCase   string
}) error {
	return filepath.Walk(dst, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}

		// Handle file name replacements first
		dir := filepath.Dir(path)
		fileName := filepath.Base(path)
		newFileName := strings.Replace(fileName, "{slug}", strings.ToLower(domain.Entity), -1)
		newFileName = strings.Replace(newFileName, "{SlugPascalCase}", domain.SlugPascalCase, -1)

		if newFileName != fileName {
			newPath := filepath.Join(dir, newFileName)
			if err := os.Rename(path, newPath); err != nil {
				return err
			}
			path = newPath
		}

		if info.IsDir() {
			return nil
		}

		data, err := os.ReadFile(path)
		if err != nil {
			return err
		}

		content := string(data)
		content = strings.Replace(content, "{Entity}", domain.Entity, -1)
		content = strings.Replace(content, "{AddonTitle}", domain.AddonTitle, -1)
		content = strings.Replace(content, "{AddonDescription}", domain.AddonDescription, -1)
		content = strings.Replace(content, "{SlugPascalCase}", domain.SlugPascalCase, -1)
		content = strings.Replace(content, "{slug}", strings.ToLower(domain.Entity), -1)
		content = strings.Replace(content, "booknetic-{slug}", fmt.Sprintf("booknetic-%s", strings.ToLower(domain.Entity)), -1)

		return os.WriteFile(path, []byte(content), info.Mode())
	})
}

func contains(s []string, str string) bool {
	for _, v := range s {
		if v == str {
			return true
		}
	}

	return false
}

func toPascalCase(kebab string) string {
	// Split by hyphens
	parts := strings.Split(kebab, "-")

	// Capitalize first letter of each part
	for i := range parts {
		if len(parts[i]) > 0 {
			parts[i] = strings.ToUpper(parts[i][:1]) + strings.ToLower(parts[i][1:])
		}
	}

	return strings.Join(parts, "")
}

func commandExists(cmd string) bool {
	_, err := exec.LookPath(cmd)
	return err == nil
}

// Find PHP using CMD's where command
func findPHP() string {
	cmd := exec.Command("cmd", "/c", "where php")
	output, err := cmd.Output()
	if err != nil {
		return ""
	}

	// Get the first line of output (first PHP found in PATH)
	paths := strings.Split(strings.TrimSpace(string(output)), "\n")
	if len(paths) > 0 {
		return strings.TrimSpace(paths[0])
	}

	return ""
}
