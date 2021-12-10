package day05

import org.jetbrains.kotlinx.multik.api.identity
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.inplace
import org.jetbrains.kotlinx.multik.ndarray.operations.math
import org.jetbrains.kotlinx.multik.ndarray.operations.plus
import readInput

fun main() {
  fun getDimensionsForMatrix(input: MutableList<MutableList<String>>): Int {
    var n = 0
    input.forEach { p ->
      // Get first point coordinates
      val (x1, y1) = p[0].split(",").map { it.toInt() }
      // Get second point coordinates
      val (x2, y2) = p[1].split(",").map { it.toInt() }
      if (x1 > n) n = x1
      if (x2 > n) n = x2
      if (y1 > n) n = y1
      if (y2 > n) n = y2
    }
    return n
  }

  fun isHorizontalOrVerticalLine(pair: MutableList<String>) =
    pair[0][0] == pair[1][0] || pair[0][2] == pair[1][2]

  fun coordStringsToPointInts(pair: MutableList<String>): MutableList<Int> {
    var coordPoints = mutableListOf<Int>()
    coordPoints.addAll(pair[0].split(",").map { it.toInt() })
    coordPoints.addAll(pair[1].split(",").map { it.toInt() })
    return coordPoints
  }

  fun isHorizontalLine(y1: Int, y2: Int) = y1 == y2

  fun part1(input: List<String>): Int {
    var coordPairs = mutableListOf<MutableList<String>>()
    input.forEach { pair -> coordPairs.add(pair.split("->").map { point -> point.trim()} as MutableList<String>) }
    // Determine dimensions of matrix
    var n = getDimensionsForMatrix(coordPairs) + 1
    println("We need a $n x $n matrix")
    // Initialize matrix with 0s
    var zeroArray = mk.zeros<Int>(n, n)
    var randIden = mk.identity<Int>(10)

    println("$n x $n Matrix: $zeroArray")

    // Go through each pair
    coordPairs.forEach { pair ->
      if (isHorizontalOrVerticalLine(pair)) {
        println("These points form a horizontal or vertical line: $pair")
        var (x1, y1, x2, y2) = coordStringsToPointInts(pair)
        // Determine if horizontal or vertical
        if (isHorizontalLine(y1, y2)) {
          // Increment and mark
          for (i in x1..x2) {
            println(zeroArray)
          }
        } else {
          // Increment and mark
        }

      } else {
        println("These points to not form a horizontal or vertical line: $pair")
      }
    }
    // Mark if x1 == x2 || y1 == y2
    return 0
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("day05/Day05_test")
  check(part1(testInput) == 0)
  check(part2(testInput) == 0)

  val input = readInput("day05/day05")
  //    println("Part 1 Answer: ${part1(input)}")
  //    println("Part 2 Answer: ${part2(input)}")
}
