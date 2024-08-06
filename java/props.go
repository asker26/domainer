package java

import (
	"fmt"
	"os/exec"
	"strings"
)

func GetProps() (group string, project string, err error) {
	fmt.Println("Trying to get gradle properties...")

	cmd := exec.Command("gradle", "properties")

	output, err := cmd.Output()
	if err != nil {
		return
	}

	gradlePropertiesOutput := string(output)

	groupLabel := "group: "
	groupIndex := strings.Index(gradlePropertiesOutput, groupLabel)
	endIndexOfGroup := strings.Index(gradlePropertiesOutput[groupIndex:], "\n")
	group = gradlePropertiesOutput[groupIndex+len(groupLabel) : groupIndex+endIndexOfGroup]

	fmt.Printf("Group \"%s\" found...\n", group)

	projectLabel := "name: "
	projectIndex := strings.Index(gradlePropertiesOutput, projectLabel)
	endIndexOfProject := strings.Index(gradlePropertiesOutput[projectIndex:], "\n")
	project = gradlePropertiesOutput[projectIndex+len(projectLabel) : projectIndex+endIndexOfProject]

	fmt.Printf("Project \"%s\" found...\n", project)

	return
}
