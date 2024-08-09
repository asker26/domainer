package utils

import (
	"domainer/files"
	"github.com/jinzhu/inflection"
	"golang.org/x/text/cases"
	"golang.org/x/text/language"
	"log"
	"strings"
)

type Entity struct {
	Value string
}

type Domain struct {
	Group   string
	Project string
	IdType  string
	Entity  Entity
}

func (e Entity) ToLower() string {
	return strings.ToLower(e.Value)
}

func (e Entity) ToUpper() string {
	return cases.Title(language.English).String(e.Value)
}

func (e Entity) ToPlural() string {
	return inflection.Plural(e.Value)
}

func (d Domain) KeyValues() map[string]files.Match {
	return map[string]files.Match{
		"#{group}":           {Value: d.Group},
		"#{project}":         {Value: d.Project},
		"#{entityLowercase}": {Value: d.Entity.ToLower()},
		"#{entity}":          {Value: d.Entity.ToUpper()},
		"#{entityPlural}":    {Value: d.Entity.ToPlural()},
		"#{idType}": {Value: d.IdType, Callback: func(content string) string {
			if strings.ToLower(d.IdType) != "uuid" {
				return content
			}

			lines := strings.Split(content, "\n")

			for i, line := range lines {
				if strings.HasPrefix(line, "import ") {
					lines = append(lines[:i+1], lines[i:]...)
					lines[i+1] = `import java.util.UUID;`
					break
				}
			}

			return strings.Join(lines, "\n")
		}},
	}
}

func (d Domain) CopyAndReplaceFile(src string, dst string) (err error) {
	//todo:// to make it more efficient copy and replace the content at the same time
	err = files.CopyFile(src, dst)
	if err != nil {
		log.Fatal(err)
		return err
	}

	return files.ReplaceAll(dst, d.KeyValues())
}

func (d Domain) ReplaceAllInText(text string) string {
	for old, match := range d.KeyValues() {
		text = strings.ReplaceAll(text, old, match.Value)
	}

	return text
}
