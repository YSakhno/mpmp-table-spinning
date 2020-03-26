/*
 * Solver for Matt Parker's Maths Puzzle (MPMP): Can you spin the table?
 *
 * See this YouTube video for more info about the puzzle itself: https://youtu.be/T29dydI97zY
 *
 * Written in 2020 by Yuri Sakhno.
 */
package io.ysakhno.mpmp.tablespin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

const val NUM_SEATS = 7
const val NUM_INITIAL_MATCHING_SEATS = 1

fun spin(offset: Int, size: Int): (Int) -> Int = { (it + offset) % size + 1 }

fun generateTableSpins(numSeats: Int) = Array(numSeats) { IntArray(numSeats, spin(it, numSeats)) }

private suspend fun FlowCollector<IntArray>.generatePermutations(available: Set<Int>, accumulated: IntArray) {
    if (available.isNotEmpty()) {
        for (next in available) {
            generatePermutations(available.minus(next), accumulated + next)
        }
    } else {
        emit(accumulated)
    }
}

suspend fun generateInvestorSeatings(numElements: Int): Flow<IntArray> = flow {
    generatePermutations(IntArray(numElements, spin(0, numElements)).toSet(), IntArray(0))
}

fun countMatching(array1: IntArray, array2: IntArray): Int {
    var count = 0;
    for (i in array1.indices) if (array1[i] == array2[i]) count++
    return count
}

fun matchingExactly(num: Int, otherArray: IntArray): suspend (IntArray) -> Boolean = { seating ->
    (countMatching(seating, otherArray) == num)
}

fun matchingLessThanTwoForAnySpin(tableSpins: Array<IntArray>): suspend (IntArray) -> Boolean = { seating ->
    tableSpins.all { tableSpin -> countMatching(seating, tableSpin) < 2 }
}

fun main() = runBlocking {
    val tableSpins = generateTableSpins(NUM_SEATS)

    generateInvestorSeatings(NUM_SEATS)
        .filter(matchingExactly(NUM_INITIAL_MATCHING_SEATS, tableSpins[0]))
        .filter(matchingLessThanTwoForAnySpin(tableSpins))
        .map { it.asList() }
        .collect { println(it) }
}
