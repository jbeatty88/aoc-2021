import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Takes a list of Strings of equal length and return vertical strings by character index mapped my index
 *
 * Example:
 *  ["Hello", "World"] -> ["HW", "eo", "lr", "ll", "od"]
 *
 * @param input List of strings of equal length
 * @return a vertical strings mapped to index
 */
fun toVerticalStringMapByIdx(input: List<String>): MutableMap<Int, String> {
    val verticalStrings = mutableMapOf<Int, String>()

    input.forEach {
        // Get the vertical binary strings for gamma and epsilon calculations
        for (i in it.indices) {
            verticalStrings[i] =
                if (verticalStrings[i].isNullOrBlank()) it[i].toString() else verticalStrings[i] + it[i]
        }
    }
    return verticalStrings
}

fun mutableListToVerticalStringMapByIdx(input: MutableList<String>): MutableMap<Int, String> {
    val verticalStrings = mutableMapOf<Int, String>()

    input.forEach {
        // Get the vertical binary strings for gamma and epsilon calculations
        for (i in it.indices) {
            verticalStrings[i] =
                if (verticalStrings[i].isNullOrBlank()) it[i].toString() else verticalStrings[i] + it[i]
        }
    }
    return verticalStrings
}

fun mapFrequenciesToIdx(verticalStrings: MutableMap<Int, String>, idxRange: Int): MutableMap<Int, MutableMap<Char, Int>> {
    val freqs: MutableMap<Int, MutableMap<Char, Int>> = HashMap()
    for (i in 0 until idxRange) {
        for (c in verticalStrings[i].toString()) {
            freqs.putIfAbsent(i, mutableMapOf(c to 0))
            freqs[i]?.putIfAbsent(c, 0)
            freqs[i]?.putIfAbsent('0', 0)
            freqs[i]?.putIfAbsent('1', 0)
            freqs[i]?.set(c, freqs[i]?.get(c)!! + 1)
        }
    }
    return freqs
}
