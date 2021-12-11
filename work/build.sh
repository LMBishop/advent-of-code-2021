#!/bin/bash

if [ -z "$1" ]; then
    echo "Build what?"
    exit 1
fi

cd ..
echo "==> Starting gradle"

[ -z "$2" ] && cmd="./gradlew aoc$1:build" || cmd="./gradlew aoc$1:build -Pvisualisation=true"

if eval $cmd; then
    cd work
    echo "==> Copying test file"
    cp ../aoc"$1"/src/main/resources/input.txt input.txt
    echo "==> Running jar"
    java -Xmx8G -jar ../aoc"$1"/build/libs/aoc"$1"-1.0.jar
fi
