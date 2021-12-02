package day02

import readInput

fun main() {
  fun part1(input: List<String>): Int {
    var y = 0
    var x = 0
    input.forEach {
      val (direction, value) = it.split(" ")
      when (direction) {
        "down" -> {
          y += value.toInt()
        }
        "up" -> {
          y -= value.toInt()
        }
        else -> {
          x += value.toInt()
        }
      }
    }
    return x * y
  }

  fun part2(input: List<String>): Int {
    var y = 0
    var x = 0
    var z = 0
    input.forEach {
      val (direction, value) = it.split(" ")
      when (direction) {
        "down" -> {
          z += value.toInt()
        }
        "up" -> {
          z -= value.toInt()
        }
        else -> {
          x += value.toInt()
          y += z * value.toInt()
        }
      }
    }
    return x * y
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("day02/Day02_test")
  check(part1(testInput) == 150)
  check(part2(testInput) == 900)

  val input = readInput("day02/day02")
  println(part1(input))
  println(part2(input))
}
