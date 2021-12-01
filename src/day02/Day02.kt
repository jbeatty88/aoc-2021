package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("day02/day02")
    println(part1(input))
    println(part2(input))
}
