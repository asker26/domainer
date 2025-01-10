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
	Group            string
	Project          string
	Entity           Entity
	AddonTitle       string
	AddonDescription string
	SlugPascalCase   string
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
		"{AddonTitle}":       {Value: d.AddonTitle},
		"{AddonDescription}": {Value: d.AddonDescription},
		"{SlugPascalCase}":   {Value: d.SlugPascalCase},
		"{slug}":             {Value: strings.ToLower(d.Entity.Value)},
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

func (d Domain) ReplacePlaceholders(dst string) error {
	// Logic to replace placeholders in the files at the destination path
	// This is a placeholder implementation
	log.Printf("Replacing placeholders in %s", dst)
	return nil
}
