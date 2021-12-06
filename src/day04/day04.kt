package day04

import readInput

fun main() {
  fun checkBoardsForWinner(gameBoards: MutableMap<Int, MutableList<MutableList<Int>>>): Int {
    //    println("Now checking boards for winner")
    var sumUnmarked = 0
    // Winning means 5 in a row vertically or horizontally
    gameBoards.forEach { k, v ->
      v.forEach { row ->
        // Horizontal Check
        //        println("Horizontal check of: $row")
        val negCount = row.filter { it < 0 }.size
        if (negCount == 5) {
          v.forEach {
            val allPos = it.filter { it >= 0 }
            sumUnmarked += allPos.sum()
          }
        }
      }
      if (sumUnmarked <= 0) {
        // Vertical Check
        for (i in 0 until 5) {
          val col = listOf<Int>(v[0][i], v[1][i], v[2][i], v[3][i], v[4][i])
          val negCount = col.filter { it < 0 }.size
          if (negCount == 5) {
            v.forEach {
              val allPos = it.filter { it >= 0 }
              sumUnmarked += allPos.sum()
            }
          }
        }
      }
    }
    return sumUnmarked
  }

  fun removeWinningBoards(
      gameBoards: MutableMap<Int, MutableList<MutableList<Int>>>
  ): MutableMap<Int, MutableList<MutableList<Int>>> {
    // Winning means 5 in a row vertically or horizontally
    var removeIdxs = mutableSetOf<Int>()
    gameBoards.forEach { (k, v) ->
      var isRemoved = false
      v.forEach { row ->
        // Horizontal Check
        val negCount = row.filter { it < 0 }.size
        if (negCount == 5) {
          isRemoved = true
          removeIdxs.add(k)
        }
      }
      if (!isRemoved) {
        // Vertical Check
        for (i in 0 until 5) {
          val col = listOf<Int>(v[0][i], v[1][i], v[2][i], v[3][i], v[4][i])
          val negCount = col.filter { it < 0 }.size
          if (negCount == 5) {
            isRemoved = true
            removeIdxs.add(k)
          }
        }
      }
    }
    removeIdxs.forEach { gameBoards.remove(it) }

    return gameBoards
  }

  fun markBoards(
      gameBoards: MutableMap<Int, MutableList<MutableList<Int>>>,
      bingoNum: Int
  ): MutableMap<Int, MutableList<MutableList<Int>>> {
    gameBoards.forEach { k, v ->
      v.forEach { row ->
        row.forEachIndexed { i, rowNum ->
          if (rowNum == bingoNum) {
            if (rowNum == 0) row[i] = -999 else row[i] = -rowNum
          }
        }
      }
    }
    return gameBoards
  }

  fun parseInputForBingoGame(
      input: List<String>
  ): Pair<List<Int>, MutableMap<Int, MutableList<MutableList<Int>>>> {
    val gameBoards = mutableMapOf<Int, MutableList<MutableList<Int>>>()
    val numberQueue = input[0].split(",").map { it.toInt() }
    var numGameBoards = -1
    // Break input into game boards
    input.forEachIndexed { i, s ->
      if (i != 0) {
        if (s == "") {
          numGameBoards++
        } else {
          val lineSplit = s.trim().split("\\s+".toRegex()).map { it.toInt() } as MutableList<Int>
          if (gameBoards[numGameBoards].isNullOrEmpty()) {
            gameBoards.putIfAbsent(numGameBoards, mutableListOf(lineSplit))
          } else {
            gameBoards[numGameBoards]?.addAll(listOf(lineSplit))
          }
        }
      }
    }
    return Pair(numberQueue, gameBoards)
  }

  fun part1(input: List<String>): Int {
    var (numberQueue, gameBoards) = parseInputForBingoGame(input)

    // For each number, mark then add to used number. Check boards after 5
    val alreadyGuessed = mutableListOf<Int>()
    numberQueue.forEach { bingoNum ->
      // Mark it on the boards
      gameBoards = markBoards(gameBoards, bingoNum)
      // Add number to list of used numbers
      alreadyGuessed.add(bingoNum)
      // Check boards for winner after 5 numbers have been used
      if (alreadyGuessed.size >= 5) {
        var winningBoardSum = checkBoardsForWinner(gameBoards)
        if (winningBoardSum > 0) {
          return winningBoardSum * bingoNum
        }
      }
    }
    return input.size
  }

  fun part2(input: List<String>): Int {
    var (numberQueue, gameBoards) = parseInputForBingoGame(input)
    // For each number, mark then add to used number. Check boards after 5
    val alreadyGuessed = mutableListOf<Int>()
    numberQueue.forEach { bingoNum ->
      // Mark it on the boards
      gameBoards = markBoards(gameBoards, bingoNum)
      // Add number to list of used numbers
      alreadyGuessed.add(bingoNum)
      // Check boards for winner after 5 numbers have been used
      if (alreadyGuessed.size >= 5) {
        if (gameBoards.size > 1) {
          gameBoards = removeWinningBoards(gameBoards)
        } else {
          // Continue until last board wins
          var sumBoard = 0
          sumBoard = checkBoardsForWinner(gameBoards)
          // Sum the board
          if (sumBoard > 0) {
            return sumBoard * bingoNum
          }
        }
      }
    }
    return -1
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("day04/Day04_test")
  check(part1(testInput) == 4512)
  check(part2(testInput) == 1924)

  val input = readInput("day04/Day04")
  println("Part 1 Answer: ${part1(input)}")
  println("Part 2 Answer: ${part2(input)}")
}
