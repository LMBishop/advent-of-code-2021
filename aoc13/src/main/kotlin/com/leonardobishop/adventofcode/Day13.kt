package com.leonardobishop.adventofcode

import java.io.File
import java.util.*

fun main() {
    val grid = Array(1400) { BooleanArray(1400) }
    val instructions = mutableListOf<String>()
    File("input.txt").forEachLine { li ->
        if (li.startsWith("fold along ")) {
            instructions.add(li.replace("fold along ", ""))
        } else if (li.isNotEmpty()) {
            val parts = li.split(",")
            grid[parts[1].toInt()][parts[0].toInt()] = true
        }
    }

    val newGrid = Array(1400) { BooleanArray(1400) }
    grid.forEachIndexed { index, line -> newGrid[index] = line.clone() }

    instructions.forEachIndexed { index, instruction ->
        if (instruction.startsWith("x=")) {
            val maxX = instruction.replace("x=", "").toInt()

            for (y in newGrid.indices) {
                for (x in newGrid[y].indices) {
                    if (x <= maxX) continue

                    if (newGrid[y][x]) {
                        newGrid[y][maxX - (x - maxX)] = true
                        newGrid[y][x] = false
                    }
                }
            }
        } else if (instruction.startsWith("y=")) {
            val maxY = instruction.replace("y=", "").toInt()

            for (y in newGrid.indices) {
                for (x in newGrid[y].indices) {
                    if (y <= maxY) continue

                    if (newGrid[y][x]) {
                        newGrid[maxY - (y - maxY)][x] = true
                        newGrid[y][x] = false
                    }
                }
            }
        }
        println("Fold ${(index + 1).toString().padStart(2)}: ${newGrid.fold(0) { acc, li -> acc + li.filter { it }.size }}")
    }

    newGrid.forEachIndexed { down, li ->
        if (down < 6) {
            li.forEachIndexed { across, it ->
                if (across < 40) if (it) print("#") else print("-")
            }
            println()
        }
    }
}
