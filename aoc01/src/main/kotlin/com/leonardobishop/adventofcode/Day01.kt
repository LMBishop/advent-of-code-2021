package com.leonardobishop.adventofcode

import java.io.File

fun main() {
    val list = mutableListOf<Int>()
    File("aoc01/src/main/resources/input.txt").useLines { lines -> lines.forEach { list.add(it.toInt()) }}
    part1(list)
    part2(list)
}

fun part1(list: List<Int>) {
    var count = 0
    for (i in 1 until list.size) {
        val a = list[i]
        val b = list[i - 1]
        if (a > b) count++
    }
    print(count)
}

fun part2(list: List<Int>) {
    var count = 0
    for (i in 3 until list.size) {
        val a = list[i - 2] + list[i - 1] + list[i]
        val b = list[i - 3] + list[i - 2] + list[i - 1]
        if (a > b) count++
    }
    print(count)
}