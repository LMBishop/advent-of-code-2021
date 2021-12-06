# Advent of Code 2021

These are my solutions written in Kotlin for Advent of Code 2021.

I am using these challenges mainly to learn and get comfortable with Kotlin 😄. I won't push solutions until the day after.

## Solutions
Since each solution is an entire Gradle submodule, here are quick links to each file:
| Day                                                                 | Source code                                                                                                                            | Input                                                                                              |
|---------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| [Day 01](https://adventofcode.com/2021/day/1): Sonar Sweep          | [Day01.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc01/src/main/kotlin/com/leonardobishop/adventofcode/Day01.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc01/resources/input.txt) |
| [Day 02](https://adventofcode.com/2021/day/2): Dive!                | [Day02.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc02/src/main/kotlin/com/leonardobishop/adventofcode/Day02.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc02/resources/input.txt) |
| [Day 03](https://adventofcode.com/2021/day/3): Binary Diagnostic    | [Day03.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc03/src/main/kotlin/com/leonardobishop/adventofcode/Day03.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc03/resources/input.txt) |
| [Day 04](https://adventofcode.com/2021/day/4): Giant Squid          | [Day04.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc04/src/main/kotlin/com/leonardobishop/adventofcode/Day04.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc04/resources/input.txt) |
| [Day 05](https://adventofcode.com/2021/day/5): Hydrothermal Venture | [Day05.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc05/src/main/kotlin/com/leonardobishop/adventofcode/Day05.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc05/resources/input.txt) |
| [Day 06](https://adventofcode.com/2021/day/6): Lanternfish          | [Day06.kt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc06/src/main/kotlin/com/leonardobishop/adventofcode/Day06.kt) | [input.txt](https://github.com/LMBishop/advent-of-code-2021/blob/master/aoc06/resources/input.txt) |

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
