#!/bin/bash

REPO_DIR="$HOME/domainer"

rsync -av --delete . "$REPO_DIR"

echo "Building the Go project..."

go build
