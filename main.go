package main

import (
	"bufio"
	"domainer/java"
	"domainer/utils"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strings"
)

func main() {

	if len(os.Args) != 2 {
		log.Fatal("Please provide entity like `domainer <entity>` ")
		return
	}

	entity := os.Args[1]

	idType, err := scanFor("Id", "Long", []string{
		"String",
		"Long",
		"Integer",
	})
	if err != nil {
		log.Fatal(err)
		return
	}

	template, err := scanFor("template", "mybatis", []string{
		"mybatis",
		"jpa",
	})
	if err != nil {
		log.Fatal(err)
		return
	}

	err = java.CheckForGradle()
	if err != nil {
		log.Fatal(err)
		return
	}

	group, project, err := java.GetProps()
	if err != nil {
		log.Fatal(err)
		return
	}

	fmt.Printf("Creating entity \"%s\"...\n", entity)

	var domain = utils.Domain{
		Group:   group,
		Project: project,
		IdType:  idType,
		Entity: utils.Entity{
			Value: entity,
		},
	}

	featureDst, err := java.GetFeatureDestination(group, project)
	if err != nil {
		log.Fatal(err)
		return
	}

	domainDst := fmt.Sprintf("%s/%s", featureDst, domain.Entity.ToLower())

	err = domain.CopyDir(getTemplatePath(template), domainDst)
	if err != nil {
		log.Fatal(err)
		return
	}
}

func scanForTemplate() (template string, err error) {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("Enter your template(Default: mybatis): ")

	template, err = reader.ReadString('\n')
	if err != nil {
		return
	}

	template = strings.Replace(template, "\n", "", -1)

	if len(template) == 0 {
		return "mybatis", nil
	}

	allowedTemplates := []string{
		"mybatis",
		"jpa",
	}

	if !utils.Contains[string](allowedTemplates, template) {
		fmt.Println(fmt.Sprintf("invalid template. Allowed templates are: %s", strings.Join(allowedTemplates, ", ")))
		return scanForTemplate()
	}

	return
}

func scanFor(key string, defaultValue string, whiteList []string) (myVal string, err error) {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print(fmt.Sprintf("Enter your %s (Default: %s): ", key, defaultValue))

	myVal, err = reader.ReadString('\n')
	if err != nil {
		return
	}

	myVal = strings.Replace(myVal, "\n", "", -1)

	if len(myVal) == 0 {
		return defaultValue, nil
	}

	if !utils.Contains[string](whiteList, myVal) {
		fmt.Println(fmt.Sprintf("invalid %s. Allowed %ss are: %s", key, key, strings.Join(whiteList, ", ")))
		return scanFor(key, defaultValue, whiteList)
	}

	return
}

func getTemplatePath(template string) string {
	ex, err := os.Executable()
	if err != nil {
		log.Fatal(err)
	}

	exPath := filepath.Dir(ex)

	return filepath.Join(exPath, fmt.Sprintf("templates/%s", template))
}

func scanForId() (idType string, err error) {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("Enter your Id type(Default: Long): ")

	idType, err = reader.ReadString('\n')
	if err != nil {
		return
	}

	idType = strings.Replace(idType, "\n", "", -1)

	if len(idType) == 0 {
		return "Long", nil
	}

	allowedTypes := []string{
		"String",
		"Long",
		"Integer",
	}

	if !utils.Contains[string](allowedTypes, idType) {
		fmt.Println(fmt.Sprintf("invalid IdType. Allowed types are: %s", strings.Join(allowedTypes, ", ")))
		return scanForId()
	}

	return
}
