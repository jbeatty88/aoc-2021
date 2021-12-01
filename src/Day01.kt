fun main() {
    fun part1(input: List<String>): Int {
        // Convert input to List<Int> for arithmetic
        val inputInt = input.map { it.toInt() }
        // Keep track of the increase count
        var incCount = 0
        // Iterate through input in groups of 2
        inputInt.forEachIndexed { i, depth ->
            // Bound check
            if (i < inputInt.size - 1) {
                if (inputInt[i+1] - depth > 0) incCount++
            }
        }
        return incCount
    }

    fun part2(input: List<String>): Int {
        // Convert input to List<Int> for arithmetic
        val inputInt = input.map { it.toInt() }
        // Keep track of the increase count
        var incCount = 0
        // Iterate through input with sliding window bound of 3
        inputInt.forEachIndexed { i, depth ->
            // Bound check
            if (i < input.size - 3) {
                // Get the sums
                val windowSum1 = depth + inputInt[i+1] + inputInt[i+2]
                val windowSum2 = inputInt[i+1] + inputInt[i+2] + inputInt[i+3]
                if (windowSum2 - windowSum1 > 0) incCount++
            }
        }
        return incCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
