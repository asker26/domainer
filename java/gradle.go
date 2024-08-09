package java

import (
	"errors"
	"fmt"
	"os/exec"
	"strings"
)

func CheckForGradle() (err error) {
	fmt.Println("Checking for gradle...")

	_, err = exec.LookPath("gradle")
	if err == nil {
		fmt.Println("Gradle found...")
		return
	}

	fmt.Println("Gradle not found...")
	fmt.Println("Would you like to install gradle?")

	var installGradle string

	_, err = fmt.Scanln(&installGradle)
	if err != nil {
		fmt.Println("Error installing gradle:", err)
		return
	}

	if strings.ToLower(installGradle) != "yes" {
		return errors.New("gradle installation is required. Failing the application")
	}

	_, err = exec.LookPath("brew")
	if err == nil {
		fmt.Println("Brew not found...")
		return errors.New("brew installation is required. Failing the application")
	}

	cmd := exec.Command("brew", "install", "gradle")

	err = cmd.Run()
	if err != nil {
		fmt.Println("Failed to install gradle:", err)
		return
	}

	fmt.Println("Gradle successfully installed...")
	return
}
