package com.leonardobishop.adventofcode

import java.io.File

fun main() {
    val fishCollection = arrayOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0)
    File("input.txt").useLines { li ->
        val ages = li.first().split(",")
        ages.forEach { age -> fishCollection[age.toInt()]++ }
    }

    countFish(fishCollection, 80)
    countFish(fishCollection, 256)
}

fun countFish(startingFish: Array<Long>, days: Int) {
    val fishCollection = startingFish.clone()
    (1..days).forEach { _ ->
        val reset = fishCollection[0]
        (0..7).forEach { fishCollection[it] = fishCollection[it + 1] }
        fishCollection[8] = reset
        fishCollection[6] += reset
    }
    println("Fish after $days days: ${fishCollection.sum()}")
}
