package day11

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("day11/Day11_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("day11/day11")
    println("Part 1 Answer: ${part1(input)}")
    println("Part 2 Answer: ${part2(input)}")
}