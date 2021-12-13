package com.leonardobishop.adventofcode

import java.io.File
import java.util.*

data class Path(val current: String, val visitedNodes: Set<String>, val visitedSmall: Boolean? = null)

fun main() {
    val adjacent = mutableMapOf<String, MutableSet<String>>()
    File("input.txt").forEachLine { li ->
        val parts = li.split('-')
        val id = parts[0]
        val exit = parts[1]
        adjacent[id]?.add(exit) ?: run { adjacent[id] = mutableSetOf(exit) }
        adjacent[exit]?.add(id) ?: run { adjacent[exit] = mutableSetOf(id) }
    }
    val small = adjacent.keys.filter { it.lowercase() == it }.toSet()

    println(partOne(adjacent, small))
    val startTime = System.currentTimeMillis()
    println(partTwo(adjacent, small))
    val endTime = System.currentTimeMillis()
    println(endTime - startTime)
}

fun partOne(adjacent: Map<String, Set<String>>, small: Set<String>): Int {
    var paths = 0

    val queue = ArrayDeque<Path>()
    queue.add(Path("start", setOf("start")))

    while (!queue.isEmpty()) {
        val path = queue.pollFirst()
        if (path.current == "end") {
            paths++
            continue
        } else {
            for (y in adjacent[path.current]!!) {
                if (!path.visitedNodes.contains(y)) {
                    val blockedNodes = path.visitedNodes.toMutableSet()
                    if (small.contains(y)) {
                        blockedNodes.add(y)
                    }
                    queue.add(Path(y, blockedNodes))
                }
            }
        }
    }

    return paths
}

fun partTwo(adjacent: Map<String, Set<String>>, small: Set<String>): Int {
    var paths = 0

    val queue = ArrayDeque<Path>()
    queue.add(Path("start", setOf("start"), false))

    while (!queue.isEmpty()) {
        val path = queue.pollFirst()
        if (path.current == "end") {
            paths++
            continue
        } else {
            for (y in adjacent[path.current]!!) {
                if (!path.visitedNodes.contains(y)) {
                    val blockedNodes = path.visitedNodes.toMutableSet()
                    val visitedSmall = path.visitedSmall
                    if (small.contains(y)) {
                        blockedNodes.add(y)
                    }
                    queue.add(Path(y, blockedNodes, visitedSmall))
                } else if (path.visitedNodes.contains(y) && !path.visitedSmall!! && y != "start" && y != "end") {
                    queue.add(Path(y, path.visitedNodes, true))
                }
            }
        }
    }

    return paths
}
