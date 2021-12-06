package day03

import mapFrequenciesToIdx
import mutableListToVerticalStringMapByIdx
import readInput
import toVerticalStringMapByIdx

fun main() {
    fun part1(input: List<String>): Int {
        val verticalStrings = toVerticalStringMapByIdx(input)

        val freqs = mapFrequenciesToIdx(verticalStrings, input[0].length)

        // Calculate the gamma and epsilon values
        val gammaEpsilon: MutableMap<String, String> = HashMap()
        for (i in 0 until input[0].length) {
            gammaEpsilon.putIfAbsent("gamma", "")
            gammaEpsilon.putIfAbsent("epsilon", "")
            if (freqs[i]?.get('0')!! > freqs[i]?.get('1')!!) {
                gammaEpsilon["gamma"] = gammaEpsilon["gamma"] + "0"
                gammaEpsilon["epsilon"] = gammaEpsilon["epsilon"] + "1"
            } else {
                gammaEpsilon["gamma"] = gammaEpsilon["gamma"] + "1"
                gammaEpsilon["epsilon"] = gammaEpsilon["epsilon"] + "0"
            }
        }

        val gammaDec = Integer.parseInt(gammaEpsilon["gamma"], 2)
        val epsilonDec = Integer.parseInt(gammaEpsilon["epsilon"], 2)

        return gammaDec * epsilonDec
    }

    fun part2(input: List<String>): Int {
        var filteredO2Input = input.toMutableList()
        var filteredCO2Input = input.toMutableList()

        var o2VerticalStrings = mutableListToVerticalStringMapByIdx(filteredO2Input)
        var co2VerticalStrings = mutableListToVerticalStringMapByIdx(filteredCO2Input)

        var o2Freqs = mapFrequenciesToIdx(o2VerticalStrings, input[0].length)
        var co2Freqs = mapFrequenciesToIdx(co2VerticalStrings, input[0].length)

        for (i in 0 until o2Freqs.size) {
            if (filteredO2Input.size == 1) continue
            val o2Criterion = if (o2Freqs[i]?.get('1')!! >= o2Freqs[i]?.get('0')!!) '1' else '0'
            filteredO2Input = filteredO2Input.filter { it[i] == o2Criterion}.toMutableList()
            o2VerticalStrings = mutableListToVerticalStringMapByIdx(filteredO2Input)
            o2Freqs = mapFrequenciesToIdx(o2VerticalStrings, input[0].length)
        }


        for (i in 0 until co2Freqs.size) {
            if (filteredCO2Input.size == 1) continue
            val co2Criterion = if (co2Freqs[i]?.get('0')!! <= co2Freqs[i]?.get('1')!!) '0' else '1'
            filteredCO2Input = filteredCO2Input.filter { it[i] == co2Criterion}.toMutableList()
            co2VerticalStrings = mutableListToVerticalStringMapByIdx(filteredCO2Input)
            co2Freqs = mapFrequenciesToIdx(co2VerticalStrings, input[0].length)
        }


        return Integer.parseInt(filteredO2Input[0], 2) * Integer.parseInt(filteredCO2Input[0], 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day03/day03")
    println(part1(input))
    println(part2(input))
}