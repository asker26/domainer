package java

import (
	"domainer/files"
	"fmt"
	"os"
	"strings"
)

func GetFeatureDestination(group, project string) (dst string, err error) {
	groupPath := strings.ReplaceAll(group, ".", "/")
	projectPath := strings.ReplaceAll(project, ".", "/")
	dst = fmt.Sprintf("src/main/java/%s/%s/feature", groupPath, projectPath)

	isPresent, err := files.IsDirPresent(dst)
	if err != nil {
		return
	}

	if isPresent {
		return
	}

	fmt.Println("Feature directory is not present...")
	fmt.Println("Creating feature directory...")

	err = os.MkdirAll(dst, 0755)
	if err != nil {
		return
	}

	return
}
