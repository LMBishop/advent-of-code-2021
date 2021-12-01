#!/bin/bash

if [ -z "$1" ]; then
    echo "Build what?"
    exit 1
fi

cd ..
echo "==> Starting gradle"

if ./gradlew aoc"$1":build; then
  echo "==> Running jar"
  cd work
  java -jar ../aoc"$1"/build/libs/aoc"$1"-1.0.jar
fi
