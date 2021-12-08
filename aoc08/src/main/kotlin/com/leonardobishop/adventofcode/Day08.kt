package com.leonardobishop.adventofcode

import java.io.File

fun main() {
    val lines = mutableMapOf<String, String>()
    File("input.txt").forEachLine { li ->
        val parts = li.split(" | ")
        lines[parts[0]] = parts[1]
    }

    partOne(lines)
    partTwo(lines)
}

fun partOne(lines: Map<String, String>) {
    var count = 0
    lines.forEach { pair ->
        count += pair.value.split(' ').count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 }
    }

    println("Times 1, 4, 7, or 8 appear: $count")
}

fun getSegments(patternString: String): Map<String, String> {
    val patterns = patternString.split(' ').map { it.toCharArray().sorted().joinToString("") }

    fun contains(a: String, b: String): Boolean = b.all { a.contains(it) }

    val digits = Array(10) { "" }

    // uniques
    digits[1] = patterns.first { it.length == 2 }
    digits[4] = patterns.first { it.length == 4 }
    digits[7] = patterns.first { it.length == 3 }
    digits[8] = patterns.first { it.length == 7 }

    // length of 6
    digits[9] = patterns.first { it.length == 6 && contains(it, digits[4]) }
    digits[0] = patterns.first { it.length == 6 && it != digits[9] && contains(it, digits[1]) }
    digits[6] = patterns.first { it.length == 6 && it != digits[9] && it != digits[0] }

    // length of 5
    digits[5] = patterns.first { it.length == 5 && contains(digits[6], it) }
    digits[3] = patterns.first { it.length == 5 && contains(it, digits[1]) }
    digits[2] = patterns.first { it.length == 5 && it != digits[5] && it != digits[3] }

    return digits.associateBy({ it }, { digits.indexOf(it).toString() })
}

fun partTwo(lines: Map<String, String>) {
    var count = 0
    lines.forEach { pair ->
        val segments = getSegments(pair.key)
        val patterns = pair.value.split(' ').map { it.toCharArray().sorted().joinToString("") }
        count += patterns.fold("") { acc, it -> acc.plus(segments[it]) }.toInt()
    }

    println("Sum of all segments: $count")
}
