package base

import (
	"domainGenerator/files"
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
	Entity  Entity
	Path    string
}

func (e Entity) ToLower() string {
	return strings.ToLower(e.Value)
}

func (e Entity) ToUpper() string {
	return cases.Title(language.English).String(e.Value)
}

func (d Domain) KeyValues() map[string]string {
	return map[string]string{
		"#{group}":           d.Group,
		"#{project}":         d.Project,
		"#{entityLowercase}": d.Entity.ToLower(),
		"#{entity}":          d.Entity.ToUpper(),
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
	for old, newTxt := range d.KeyValues() {
		text = strings.ReplaceAll(text, old, newTxt)
	}

	return text
}
