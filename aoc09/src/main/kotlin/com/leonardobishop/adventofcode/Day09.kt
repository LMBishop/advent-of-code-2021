package com.leonardobishop.adventofcode

import java.io.File

typealias Coordinate = Pair<Int, Int>

fun main() {
    val lines = mutableListOf<List<Int>>()
    File("input.txt").forEachLine { li ->
        val ints = mutableListOf<Int>()
        li.forEach { ints.add(it.toString().toInt()) }
        lines.add(ints)
    }

    partOne(lines)
    partTwo(lines)
}

fun partOne(lines: List<List<Int>>) {
    val numRows = lines.size
    val numCols = lines[0].size

    val result = asCoordinateList(numRows, numCols)
        .sumOf { (row, col) -> riskLevelOf(row, col, numRows, numCols, lines) }

    println("Risk level: $result")
}

fun partTwo(lines: List<List<Int>>) {
    val rows = lines.size
    val columns = lines[0].size

    val basinSizes = asCoordinateList(rows, columns)
        .filter { (y, x) -> riskLevelOf(y, x, rows, columns, lines) > 0 }
        .map { getBasin(it, rows, columns, lines) }
        .map { it.size }
        .sortedDescending()

    val result = basinSizes.take(3).fold(1) { product, it -> product * it }

    println("Product of three largest basins: $result")
}

fun riskLevelOf(y: Int, x: Int, numRows: Int, numCols: Int, lines: List<List<Int>>): Int {
    val height = lines[y][x]

    return if (neighboursOf(y, x, numRows, numCols).all { (y, x) -> height < lines[y][x] }) height + 1 else 0
}

fun asCoordinateList(rows: Int, columns: Int): List<Coordinate> {
    return (0 until rows).flatMap { row -> (0 until columns).map { col -> Pair(row, col) } }
}

fun neighboursOf(y: Int, x: Int, rows: Int, columns: Int): List<Coordinate> {
    val neighbours = mutableListOf<Coordinate>()

    if (y > 0) neighbours.add(Pair(y - 1, x))
    if (y < rows - 1) neighbours.add(Pair(y + 1, x))
    if (x > 0) neighbours.add(Pair(y, x - 1))
    if (x < columns - 1) neighbours.add(Pair(y, x + 1))
    
    return neighbours
}

fun getBasin(start: Coordinate, rows: Int, columns: Int, lines: List<List<Int>>): Set<Coordinate> {
    val basinCoordinates = mutableSetOf<Coordinate>()

    var todo = mutableListOf(start)
    val visited = mutableSetOf<Coordinate>()

    while (todo.isNotEmpty()) {
        val current = todo[0]
        val (y, x) = current
        val height = lines[y][x]
        todo = todo.drop(1).toMutableList()

        basinCoordinates.add(current)
        visited.add(current)

        val neighbours = neighboursOf(y, x, rows, columns)
            .filter { it !in visited }
            .filter { (y, x) -> lines[y][x] in (height + 1)..8 }
        todo.addAll(neighbours)
    }

    return basinCoordinates
}
