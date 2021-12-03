package com.leonardobishop.adventofcode

import java.io.File
import java.util.function.BiPredicate
import kotlin.math.pow

fun main() {
    val list = mutableListOf<String>()
    File("input.txt").useLines { lines -> lines.forEach { list.add(it) }}
    partOne(list)
    partTwo(list)
}

fun count(list: List<String>, pos: Int): Pair<Int, Int> {
    var zero = 0
    var one = 0
    for (line in list) {
        if (line[pos] == '0') {
            zero++
        } else {
            one++
        }
    }
    return Pair(zero, one)
}

fun partOne(list: List<String>) {
    var gammaBitString = ""
    for (i in 0 until list[0].length) {
        val (zero, one) = count(list, i)
        gammaBitString += if (one > zero) "1" else "0"
    }
    val gamma = gammaBitString.toInt(2)
    val epsilon = gamma.inv() and (2.0.pow(gammaBitString.length).toInt() - 1)

    println("$gamma gamma / $epsilon epsilon")
    println("${gamma * epsilon} power consumption")
}

fun partTwo(list: List<String>) {
    fun Boolean.asChar(): Char = if (this) '1' else '0'

    fun filterToOne(list: List<String>, what: Char, pos: Int): List<String> {
        val mutableList = list.toMutableList()
        for (i in list.indices) {
            if (mutableList.size == 1) {
                break
            } else if (list[i][pos] != what) {
                mutableList.remove(list[i])
            }
        }
        return mutableList.toList()
    }

    fun reduceList(list: List<String>, highBitPredicate: BiPredicate<Int, Int>): List<String> {
        var filteredList = list.toList()
        for (i in 0 until list[0].length) {
            val (zero, one) = count(filteredList, i)
            filteredList = filterToOne(filteredList, highBitPredicate.test(zero, one).asChar(), i)
            if (filteredList.size == 1) {
                break
            }
        }
        return filteredList
    }

    val oxygenList = reduceList(list) { zero, one -> one > zero || zero == one }
    val carbonList = reduceList(list) { zero, one -> zero > one }

    val oxygen = oxygenList[0].toInt(2)
    val carbon = carbonList[0].toInt(2)

    println("$oxygen oxy / $carbon carbon")
    println("${oxygen * carbon} life support rating")
}
