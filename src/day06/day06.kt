package day06

import readInput

fun main() {
    fun getFishCountAfterDays(input: List<String>, days: Int): Long {
        val lanternFishes = input[0].split(",").map { it.toLong() }.toMutableList()
        var fishCount = lanternFishes.size.toLong()
        var fishMapping = lanternFishes.groupingBy { it }.eachCount().toMutableMap()

        for (t in 0..8) {
            fishMapping.putIfAbsent(t.toLong(), 0)
        }
        var fishMappingLong: MutableMap<Long, Long> = fishMapping.mapValues { it.value.toLong() }.toMutableMap()
        var sortedFishMap = fishMappingLong.toSortedMap()
        for (i in 1..days) {
            var fishMappingCpy = sortedFishMap.toSortedMap()
            if (sortedFishMap[0]!! > 0) {
                fishCount += sortedFishMap[0]!!
                fishMappingCpy[8] = sortedFishMap[0]!!
                fishMappingCpy[6] = sortedFishMap[0]?.plus(sortedFishMap[7]!!) ?: fishMappingCpy[6]!!
            } else {
                fishMappingCpy[8] = 0
                fishMappingCpy[6] = sortedFishMap[7]!!
            }
            fishMappingCpy[0] = sortedFishMap[1]!!
            fishMappingCpy[1] = sortedFishMap[2]!!
            fishMappingCpy[2] = sortedFishMap[3]!!
            fishMappingCpy[3] = sortedFishMap[4]!!
            fishMappingCpy[4] = sortedFishMap[5]!!
            fishMappingCpy[5] = sortedFishMap[6]!!
            fishMappingCpy[7] = sortedFishMap[8]!!
            sortedFishMap = fishMappingCpy
        }
        return fishCount
    }

    fun part1(input: List<String>): Int {
        /*println("Initial State $lanternFishes")
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
        }*/

        return getFishCountAfterDays(input, 80).toInt()
    }

    fun part2(input: List<String>): Long {
        return getFishCountAfterDays(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("day06/day06")
    println("Part 1 Answer: ${part1(input)}")
    println("Part 2 Answer: ${part2(input)}")
}
