package day05

import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
import readInput
import kotlin.math.abs

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

  fun isHorizontalOrVerticalLine(pair: MutableList<String>): Boolean {
    val (x1, y1) = pair[0].split(",").map { it.trim() }
    val (x2, y2) = pair[1].split(",").map { it.trim() }
    return x1 == x2 || y1 == y2
  }
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

//    println("$n x $n Matrix: $zeroArray")
    var intersectCount = 0
    // Go through each pair
    coordPairs.forEach { pair ->
      if (isHorizontalOrVerticalLine(pair)) {
//        println("These points form a horizontal or vertical line: $pair")
        val (x1, y1, x2, y2) = coordStringsToPointInts(pair)
        // Determine if horizontal or vertical
        if (isHorizontalLine(y1, y2)) {
          // Increment and mark
          if (x1 > x2) {
            for (i in x2..x1) {
              var matrixVal = zeroArray[y1, i]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[y1, i] = matrixVal
            }
          } else {
            for (i in x1..x2) {
              var matrixVal = zeroArray[y1, i]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[y1, i] = matrixVal
            }
          }
        } else {
          if (y1 > y2) {
            for (i in y2..y1) {
              var matrixVal = zeroArray[i, x1]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[i, x1] = matrixVal
            }
          } else {
            for (i in y1..y2) {
              var matrixVal = zeroArray[i, x1]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[i, x1] = matrixVal
            }
          }
          // Increment and mark
        }

      } else {
        println("These points to not form a horizontal or vertical line: $pair")
      }
    }
    // Mark if x1 == x2 || y1 == y2
    return intersectCount
  }

  fun isDiagonal(pair: MutableList<String>): Boolean {
    val (x1, y1) = pair[0].split(",").map { it.trim().toInt() }
    val (x2, y2) = pair[1].split(",").map { it.trim().toInt() }
    return abs(x1 - x2) == abs(y1 - y2)
  }

  fun part2(input: List<String>): Int {
    // Diagonal 45deg: |x1-x2| == |y1 - y2|
    var coordPairs = mutableListOf<MutableList<String>>()
    input.forEach { pair -> coordPairs.add(pair.split("->").map { point -> point.trim()} as MutableList<String>) }
    // Determine dimensions of matrix
    val n = getDimensionsForMatrix(coordPairs) + 1
    println("We need a $n x $n matrix")
    // Initialize matrix with 0s
    var zeroArray = mk.zeros<Int>(n, n)

    var intersectCount = 0
    // Go through each pair
    coordPairs.forEach { pair ->
      if (isHorizontalOrVerticalLine(pair)) {
//        println("These points form a horizontal or vertical line: $pair")
        val (x1, y1, x2, y2) = coordStringsToPointInts(pair)
        // Determine if horizontal or vertical
        if (isHorizontalLine(y1, y2)) {
          // Increment and mark
          if (x1 > x2) {
            for (i in x2..x1) {
              var matrixVal = zeroArray[y1, i]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[y1, i] = matrixVal
            }
          } else {
            for (i in x1..x2) {
              var matrixVal = zeroArray[y1, i]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[y1, i] = matrixVal
            }
          }
        } else {
          if (y1 > y2) {
            for (i in y2..y1) {
              var matrixVal = zeroArray[i, x1]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[i, x1] = matrixVal
            }
          } else {
            for (i in y1..y2) {
              var matrixVal = zeroArray[i, x1]
              if (matrixVal == 1) intersectCount++
              matrixVal += 1
              zeroArray[i, x1] = matrixVal
            }
          }
        }
      } else if (isDiagonal(pair)) {
        var (x1, y1, x2, y2) = coordStringsToPointInts(pair)
        val dif = abs(x1 - x2)
        val xIncrement = x1 < x2
        val yIncrement = y1 < y2
        for (i in 0..dif) {
          var matrixVal = zeroArray[y1, x1]
          if (matrixVal == 1) intersectCount++
          matrixVal += 1
          zeroArray[y1, x1] = matrixVal
          if (xIncrement) x1++
          else x1--
          if (yIncrement) y1++
          else y1--
        }
      } else {
        println("These points to not form a horizontal, vertical, or diagonal line: $pair")
      }
    }
    return intersectCount
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("day05/Day05_test")
  check(part1(testInput) == 5)
  check(part2(testInput) == 12)

  val input = readInput("day05/day05")
      println("Part 1 Answer: ${part1(input)}")
      println("Part 2 Answer: ${part2(input)}")
}
