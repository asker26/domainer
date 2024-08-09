package files

import (
	"os"
	"strings"
)

type Match struct {
	Value    string
	Callback func(content string) string
}

func ReplaceAll(filename string, matchers map[string]Match) error {
	// Read the file
	content, err := os.ReadFile(filename)
	if err != nil {
		return err
	}

	strContent := string(content)

	for old, match := range matchers {
		if match.Callback != nil && strings.Contains(strContent, old) {
			strContent = match.Callback(strContent)
		}

		strContent = strings.ReplaceAll(strContent, old, match.Value)
	}

	// Write new content back to the file
	err = os.WriteFile(filename, []byte(strContent), 0)
	if err != nil {
		return err
	}

	return nil
}
