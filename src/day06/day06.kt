package day06

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var lanternFishes = input[0].split(",").map { it.toInt() }.toMutableList()
        println("Initial State $lanternFishes")
        for (i in 1..80) {
            var lanternFishSnapshot: List<Int> = ArrayList(lanternFishes)
            lanternFishSnapshot.forEachIndexed {fishIdx, fishTimer ->
                if (fishTimer == 0) {
                    lanternFishes.add(8)
                    lanternFishes[fishIdx] = 6
                } else {
                    lanternFishes[fishIdx]--
                }
            }
        }
        return lanternFishes.size
    }

    fun part2(input: List<String>): Long {
        var lanternFishes = input[0].split(",").map { it.toInt() }.toMutableList()
        println("Initial State $lanternFishes")
        for (i in 1..256) {
            var lanternFishSnapshot: List<Int> = ArrayList(lanternFishes)
            lanternFishSnapshot.forEachIndexed {fishIdx, fishTimer ->
                if (fishTimer == 0) {
                    lanternFishes.add(8)
                    lanternFishes[fishIdx] = 6
                } else {
                    lanternFishes[fishIdx]--
                }
            }
        }
        return lanternFishes.size.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/Day06_test")
    check(part1(testInput) == 5934)
//    check(part2(testInput) == 26984457539)

    val input = readInput("day06/day06")
    println("Part 1 Answer: ${part1(input)}")
//    println("Part 2 Answer: ${part2(input)}")
}
