rootProject.name = "advent-of-code-2021"
val submodules = mutableListOf<String>()
(1..25).forEach { e -> submodules.add("aoc%02d".format(e)) }
include(*submodules.toTypedArray())
