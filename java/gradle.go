package java

import (
	"fmt"
	"os/exec"
)

func CheckForGradle() {
	fmt.Println("Checking for gradle...")

	_, err := exec.LookPath("gradle")
	if err == nil {
		fmt.Println("Gradle found...")
		return
	}

	fmt.Println("Gradle not found, installing...")
	cmd := exec.Command("brew", "install", "gradle")

	err = cmd.Run()
	if err != nil {
		fmt.Println("Failed to install gradle:", err)
		return
	}

	fmt.Println("Gradle successfully installed...")

}
