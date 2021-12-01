# Advent of Code 2021

These are my solutions written in Kotlin for Advent of Code 2021.

I am using these challenges mainly to learn Kotlin 😄

## Building and testing
To clone the repository:
```
$ git clone https://github.com/LMBishop/advent-of-code-2021/
$ cd advent-of-code-2021
```
### Using Gradle
Linux / macOS:
```
$ ./gradlew build
```
Windows:
```
gradlew build
```

All output jars will be in `build/` of the respective submodules.

### Using the helper script
```
$ cd work
$ ./build.sh <day code (e.g. 01, 02, ..., 25)>
```

The working directory will be `work/`, so place input files in a file named `input.txt` there.