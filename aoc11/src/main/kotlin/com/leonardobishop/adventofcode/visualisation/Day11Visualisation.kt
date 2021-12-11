package com.leonardobishop.adventofcode.visualisation

import java.io.File

typealias Coordinate = Pair<Int, Int>

const val VISUALISE_GRID = true

fun main() {
    val grid = mutableListOf<MutableList<Int>>()
    File("input.txt").forEachLine { li ->
        val ints = mutableListOf<Int>()
        li.forEach { ints.add(it.toString().toInt()) }
        grid.add(ints)
    }

    println("Flashes after 100 steps: ${simulate(grid.map { it.toMutableList() }, 100)}")
    println("Steps until synchronicity: ${simulate(grid.map { it.toMutableList() })}")
}

fun simulate(grid: List<MutableList<Int>>, limit: Int? = null): Int {
    var flashes = 0
    for (i in (1..(limit ?: 99999))) {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                grid[y][x]++
            }
        }

        val flashed = mutableSetOf<Coordinate>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val coordinate = Coordinate(x, y)
                if (grid[y][x] > 9) {
                    flash(coordinate, grid, flashed)
                }
            }
        }

        flashes += flashed.size

        flashed.forEach {
            grid[it.second][it.first] = 0
        }

        if (limit == null && (flashed.size == grid.size * grid[0].size)) {
            return i
        }

        if (VISUALISE_GRID && limit != null) visualiseGrid(i, grid)
    }
    return flashes
}

fun flash(coordinate: Coordinate, grid: List<MutableList<Int>>, flashed: MutableSet<Coordinate>) {
    if (flashed.contains(coordinate)) {
        return
    }
    flashed.add(coordinate)

    adjacent(coordinate).forEach {
        val (x, y) = it
        if (y >= 0 && y < grid.size && x >= 0 && x < grid[0].size) {
            grid[y][x]++
            if (grid[y][x] > 9) {
                flash(it, grid, flashed)
            }
        }
    }
}

fun adjacent(coordinate: Coordinate): List<Coordinate> {
    val (x, y) = coordinate
    return listOf(
        Coordinate(x - 1, y),
        Coordinate(x - 1, y - 1),
        Coordinate(x - 1, y + 1),
        Coordinate(x, y - 1),
        Coordinate(x, y + 1),
        Coordinate(x + 1 , y + 1),
        Coordinate(x + 1 , y),
        Coordinate(x + 1 , y - 1)
    )
}

const val RESET = "\u001b[0m"
const val BOLD = "\u001b[1m"

fun visualiseGrid(step: Int, grid: List<List<Int>>) {
    println("Step $step:")
    grid.forEach { line ->
        line.forEach {
            if (it == 0) {
                print("$BOLD$it$RESET")
            } else {
                print(it)
            }
        }
        println()
    }
    println()
}
