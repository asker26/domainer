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
		"UUID",
	})
	if err != nil {
		log.Fatal(err)
		return
	}

	fmt.Println(idType)

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
