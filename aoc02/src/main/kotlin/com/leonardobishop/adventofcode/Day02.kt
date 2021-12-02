package com.leonardobishop.adventofcode

import java.io.File

fun main() {
    val list = mutableListOf<String>()
    File("input.txt").useLines { lines -> lines.forEach { list.add(it) }}
    part1(list)
    part2(list)
}

fun part1(list: List<String>) {
    var hor = 0
    var depth = 0
    for (line in list) {
        val cmd = line.split(' ')[0]
        val num = line.split(' ')[1].toInt()
        when (cmd) {
            "forward" -> {
                hor += num
            }
            "down" -> {
                depth += num
            }
            "up" -> {
                depth -= num
            }
        }
    }
    println(hor)
    println(depth)
    println(hor*depth)
}

fun part2(list: List<String>) {
    var hor = 0
    var depth = 0
    var aim = 0
    for (line in list) {
        val cmd = line.split(' ')[0]
        val num = line.split(' ')[1].toInt()
        when (cmd) {
            "forward" -> {
                hor += num
                depth += aim * num
            }
            "down" -> {
                aim += num
            }
            "up" -> {
                aim -= num
            }
        }
    }
    println(hor)
    println(depth)
    println(hor*depth)
}