package com.leonardobishop.adventofcode

import java.io.File
import kotlin.math.abs

fun main() {
    val crabs = mutableListOf<Long>()
    File("input.txt").useLines { li ->
        val locations = li.first().split(",")
        locations.forEach { hor -> crabs.add(hor.toLong()) }
    }

    val max: Int = crabs.maxOrNull()!!.toInt()

    println("Crab distances: ${countCrabDistances(max, crabs) { crab, dest ->  abs(crab - dest) }}")
    println("Fat crab distances: ${countCrabDistances(max, crabs) { crab, dest ->  (0..abs(crab - dest)).sum() }}")
}

fun countCrabDistances(max: Int, crabs: List<Long>, accumulator: ((Long, Int) -> Long)): Long {
    val crabDistances = LongArray(max)

    (0 until max).forEach { dest ->
        crabDistances[dest] = crabs.reduce { acc, crab -> acc + accumulator.invoke(crab, dest) }
    }

    return crabDistances.minOrNull()!!
}
