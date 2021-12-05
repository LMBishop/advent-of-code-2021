package com.leonardobishop.adventofcode

import java.io.File
import kotlin.math.abs

data class Line(val start: Pair<Int, Int>, val end: Pair<Int, Int>, val vector: Pair<Int, Int>)

fun main() {
    val lines = mutableListOf<Line>()
    File("input.txt").useLines { li -> li.forEach {
        val coords = it.split(" -> ")
        val start = Pair(coords[0].split(',')[0].toInt(), coords[0].split(',')[1].toInt())
        val end = Pair(coords[1].split(',')[0].toInt(), coords[1].split(',')[1].toInt())
        val vector = Pair(start.first - end.first, start.second - end.second)
        lines.add(Line(start, end, vector))
    } }

    partOne(lines)
    partTwo(lines)
}

fun partOne(lines: List<Line>) {
    val straights = lines.filter {
        val (x, y) = it.vector
        x == 0 || y == 0
    }
    val grid = Array(1000) { IntArray(1000) }

    straights.forEach {
        val (xVec, yVec) = it.vector
        if (xVec == 0) {
            (it.start.second.coerceAtMost(it.end.second)..it.start.second.coerceAtLeast(it.end.second)).forEach {
                    y -> grid[y][it.start.first]++
            }
        } else if (yVec == 0) {
            (it.start.first.coerceAtMost(it.end.first)..it.start.first.coerceAtLeast(it.end.first)).forEach {
                    x -> grid[it.start.second][x]++
            }
        }
    }

    var overlap = 0
    grid.forEach { it.filter { num -> num >= 2 }.forEach { _ -> overlap++ } }
    println("Overlapping points in straights: $overlap")
}

fun partTwo(lines: List<Line>) {
    val cardinals = lines.filter {
        val (x, y) = it.vector
        x == 0 || y == 0 || abs(x) == abs(y)
    }
    val grid = Array(1000) { IntArray(1000) }

    cardinals.forEach {
        val (xVec, yVec) = it.vector
        if (xVec == 0) {
            (it.start.second.coerceAtMost(it.end.second)..it.start.second.coerceAtLeast(it.end.second)).forEach {
                    y -> grid[y][it.start.first]++
            }
        } else if (yVec == 0) {
            (it.start.first.coerceAtMost(it.end.first)..it.start.first.coerceAtLeast(it.end.first)).forEach {
                    x -> grid[it.start.second][x]++
            }
        } else {
            val xDir = if (xVec > 0) -1 else 1
            val yDir = if (yVec > 0) -1 else 1
            for (i in 0..(it.start.first.coerceAtLeast(it.end.first) - it.start.first.coerceAtMost(it.end.first))) {
                grid[it.start.second + (i * yDir)][it.start.first + (i * xDir)]++
            }
        }
    }

    var overlap = 0
    grid.forEach { it.filter { num -> num >= 2 }.forEach { _ -> overlap++ } }
    println("Overlapping points in cardinals: $overlap")
}