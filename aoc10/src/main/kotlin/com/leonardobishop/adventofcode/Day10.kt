package com.leonardobishop.adventofcode

import java.io.File
import java.util.*

fun main() {
    val lines = mutableListOf<List<Char>>()
    File("input.txt").forEachLine { li ->
        val ints = mutableListOf<Char>()
        li.forEach { ints.add(it) }
        lines.add(ints)
    }

    val incompleteLines = partOne(lines)
    partTwo(incompleteLines)
}


val openers = setOf('(', '[', '{', '<')
val closers = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

fun partOne(lines: List<List<Char>>): List<List<Char>> {
    val stack = Stack<Char>()
    var totalScore = 0

    fun scoreOf(char: Char): Int = if (char == ')') 3 else if (char == ']') 57 else if (char == '}') 1197 else 25137

    val brokenLines = mutableListOf<List<Char>>()
    lines.forEach { line ->
        var score: Int? = null
        line.forEach loop@ {
            if (openers.contains(it)) {
                stack.push(it)
            } else {
                val char = stack.pop()
                if (it != closers[char]) {
                    score = scoreOf(it)
                    brokenLines.add(line)
                    return@loop
                }
            }
        }
        stack.clear()

        if (score != null) {
            totalScore += score!!
        }
    }

    println("Score of corrupt lines: $totalScore")
    return lines.filter { !brokenLines.contains(it) }
}

fun partTwo(lines: List<List<Char>>) {
    val stack = Stack<Char>()

    fun scoreOf(char: Char): Int = if (char == ')') 1 else if (char == ']') 2 else if (char == '}') 3 else 4

    val scores = mutableListOf<Long>()
    lines.forEach { line ->
        var score: Long = 0
        line.forEach loop@ {
            if (openers.contains(it)) {
                stack.push(it)
            } else {
                stack.pop()
            }
        }
        stack.reversed().forEach {
            score = score * 5 + scoreOf(closers[it]!!)
        }
        stack.clear()

        scores.add(score)
    }

    println("Middle score: ${scores.sorted()[scores.size / 2]}")
}