package utils

import (
	"os"
	"path/filepath"
)

func (d Domain) CopyDir(src string, dst string) error {
	entries, err := os.ReadDir(src)
	if err != nil {
		return err
	}

	err = os.MkdirAll(dst, 0755)
	if err != nil {
		return err
	}

	for _, entry := range entries {
		srcPath := filepath.Join(src, entry.Name())

		name := d.ReplaceAllInText(entry.Name())
		dstPath := filepath.Join(dst, name)

		fileInfo, err := os.Stat(srcPath)
		if err != nil {
			return err
		}

		switch fileInfo.Mode() & os.ModeType {
		case os.ModeDir:
			if err = d.CopyDir(srcPath, dstPath); err != nil {
				return err
			}
		case os.ModeSymlink:
			if err = d.CopySymLink(srcPath, dstPath); err != nil {
				return err
			}
		default:
			if err = d.CopyAndReplaceFile(srcPath, dstPath); err != nil {
				return err
			}
		}
	}
	return nil
}

func (d Domain) CopySymLink(src, dst string) error {
	link, err := os.Readlink(src)
	if err != nil {
		return err
	}
	return os.Symlink(link, dst)
}
