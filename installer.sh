#!/bin/bash

install_go() {
    echo "Go is not installed. Installing Go..."

    brew install go

    echo "Go installed successfully."

    echo "export PATH=$PATH:/usr/local/go/bin" >> "$HOME/.zshrc"

    source "$HOME/.zshrc"
}

if ! command -v go &> /dev/null
then
  if ! command -v brew &> /dev/null
  then echo "You need to have homebrew installed to download go..." && exit
  else
    install_go
  fi
else
    echo "Go is already installed."
fi

# Clone the repository
REPO_URL="https://github.com/asker26/domainer"
REPO_DIR="$HOME/domainer"

if [ -d "$REPO_DIR" ]; then
    echo "Repository already cloned. Updating repository..."
    cd "$REPO_DIR" || (echo "Can't go to repository directory" && exit)
    git pull
else
    echo "Cloning repository..."
    cd "$HOME" || (echo "Can't go to home" && exit)
    git clone "$REPO_URL"
fi

cd "$REPO_DIR" || exit

#Build
echo "Building the Go project..."
go build

echo "export PATH=\$PATH:$(pwd)" >> "$HOME/.zshrc"

source "$HOME/.zshrc"

echo "Setup completed successfully. The repository has been added to PATH."