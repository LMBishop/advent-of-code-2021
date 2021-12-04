package com.leonardobishop.adventofcode

import java.io.File

data class Card(val originals: List<Int>, val winningSequences: MutableList<List<Int>>) {
    companion object {
        fun fromGrid(grid: List<String>): Card {
            val rows = grid.map { line -> line.trim().split("\\s+".toRegex()).map { it.toInt() } }.toMutableList()
            val originals = rows.flatten()
            for (column in 0..4) {
                rows.add(listOf(rows[0][column], rows[1][column], rows[2][column], rows[3][column], rows[4][column]))
            }
            return Card(originals, rows)
        }
    }

    fun mark(number: Int) {
        for (i in winningSequences.indices) {
            winningSequences[i] = winningSequences[i].filter { it != number }
        }
    }

    fun bingo(): Boolean = winningSequences.any { it.isEmpty() }
}

fun main() {
    val grids = File("input.txt").readText().split("\n\n")

    val cards = mutableListOf<Card>()
    val draws = grids[0].split(',').map { it.toInt() }

    grids.subList(1, grids.size).forEach { cards.add(Card.fromGrid(it.split("\n"))) }

    partOne(cards, draws)
    partTwo(cards, draws)
}

fun partOne(cards: List<Card>, draws: List<Int>) {
    val processedNumbers = mutableListOf<Int>()
    draws.forEach { number ->
        cards.forEach { card ->
            if (card.bingo()) {
                val sum = card.originals.filter { !processedNumbers.contains(it) }.sum()
                println("Score of first card: ${sum * processedNumbers.last()}")

                return@partOne
            }

            card.mark(number)
        }
        processedNumbers.add(number)
    }
}

fun partTwo(cards: List<Card>, draws: List<Int>) {
    val processedNumbers = mutableListOf<Int>()
    val winners = mutableListOf<Card>()
    draws.forEach { number ->
        cards.filter { !winners.contains(it) }.forEach { card ->
            if (card.bingo()) {
                winners.add(card)
            }

            if (winners.size == cards.size) {
                val sum = winners.last().originals.filter { !processedNumbers.contains(it) }.sum()
                println("Score of last card: ${sum * processedNumbers.last()}")

                return@partTwo
            }

            card.mark(number)
        }
        processedNumbers.add(number)
    }
}
