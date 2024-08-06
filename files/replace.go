package files

import (
	"os"
	"strings"
)

func ReplaceAll(filename string, matchers map[string]string) error {
	// Read the file
	content, err := os.ReadFile(filename)
	if err != nil {
		return err
	}

	strContent := string(content)

	for old, newTxt := range matchers {
		strContent = strings.ReplaceAll(strContent, old, newTxt)
	}

	// Write new content back to the file
	err = os.WriteFile(filename, []byte(strContent), 0)
	if err != nil {
		return err
	}

	return nil
}
