package main

import (
	. "domainGenerator/base"
	"fmt"
	"log"
	"os"
	"path/filepath"
)

func main() {
	var group, project, entity string

	// Get inputs from user
	fmt.Print("Enter group: ")
	_, err := fmt.Scanln(&group)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Print("Enter project: ")
	_, err = fmt.Scanln(&project)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Print("Enter entity: ")
	_, err = fmt.Scanln(&entity)
	if err != nil {
		log.Fatal(err)
	}

	var domain = Domain{
		Group:   group,
		Project: project,
		Entity: Entity{
			Value: entity,
		},
	}

	err = os.RemoveAll(domain.Entity.ToLower())
	if err != nil {
		log.Fatal(err)
	}

	err = domain.CopyDir(getExamplePath(), domain.Entity.ToLower())
	if err != nil {
		log.Fatal(err)
		return
	}
}

func getExamplePath() string {
	ex, err := os.Executable()
	if err != nil {
		log.Fatal(err)
	}

	exPath := filepath.Dir(ex)

	return filepath.Join(exPath, "example")
}
