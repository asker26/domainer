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

	idType, err := scanForId()
	if err != nil {
		log.Fatal(err)
		return
	}

	java.CheckForGradle()

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

	err = domain.CopyDir(getTemplatePath(), domainDst)
	if err != nil {
		log.Fatal(err)
		return
	}
}

func getTemplatePath() string {
	ex, err := os.Executable()
	if err != nil {
		log.Fatal(err)
	}

	exPath := filepath.Dir(ex)

	return filepath.Join(exPath, "templates/mybatis")
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
