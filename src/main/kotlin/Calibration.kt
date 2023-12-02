// Day 1 Solution
class Calibration (private val input: MutableList<String>) {
    private val stringToNumberMap: Map<String, Int> = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    private var convertLettersToDigits = input

    private fun convertLettersToDigits(): MutableList<String> {
        for (mapEntry in stringToNumberMap.entries) {
            convertLettersToDigits =
                convertLettersToDigits.map { it -> it.replace(mapEntry.key, mapEntry.key.dropLast(1) + mapEntry.value.toString() + mapEntry.key.last()) }
                    .toMutableList()
        }
        return convertLettersToDigits
    }

    private fun filterOutDigits(input: MutableList<String>): MutableList<String> {
        return input.map { it -> it.filter { it.isDigit() } }.toMutableList()
    }
    private fun recoverCalibration(): List<Int> {
        val convertLettersToDigits = convertLettersToDigits()
        val keepOnlyDigits = convertLettersToDigits.map { it -> it.filter { it.isDigit() } }
        return keepOnlyDigits.map { it.first() + it.last().toString() }.map { it.toInt() }
    }

    fun addUpCalibratedValues(): Int {
        return recoverCalibration().sum()
    }
}