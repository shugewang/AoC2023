// Day 3 Solution
class Engine (var input: MutableList<String>) {
    private var partNumbers = mutableListOf<PartNumber>()
    private var gears = mutableListOf<Gear>()
    private var gearAndAssociatedNumber = mutableMapOf<Gear, MutableList<Int>>()

    init {
        padInputWithDots()
        getGears()
        getNumbers()
    }

    private fun padInputWithDots() {
        input = input.map { ".$it." }.toMutableList()
        input.add(0, ".".repeat(input[0].length))
        input.add(".".repeat(input[0].length))
    }

    private fun getGears() {
        for (row in input.indices) {
            for (col in input[row].indices) {
                if (isGear(input[row][col])) {
                    gears.add(Gear(row, col))
                }
            }
        }
    }

    private fun findRealGears() {
        gears.map { gearAndAssociatedNumber[it] = mutableListOf(); findAdjacentNumbers(it) }
    }

    fun getGearRatios(): Int {
        findRealGears()
        var sum = 0
        gearAndAssociatedNumber.map { if (it.value.size == 2) { sum += it.value.reduce { acc, i -> acc * i } } }
        return sum
    }

    private fun findAdjacentNumbers(gear: Gear) {
        for (number in partNumbers) {
            if (gear.row in number.row-1..number.row+1 && gear.col in number.colStart-1..number.colEnd+1) {
                gearAndAssociatedNumber[gear]?.add(number.value.toInt())
            }
        }
    }

    private fun getNumbers() {
        var number = ""
        var colStart = 0
        var colEnd = 0

        for (row in input.indices) {
            for (col in input[row].indices) {
                if (input[row][col].isDigit()) {
                    if (isStartOfNumber(row, col) && isEndOfNumber(row, col)) {
                        number += input[row][col]
                        colStart = col
                        partNumbers.add(PartNumber(number, row, colStart, col))
                        number = ""
                    } else if (isStartOfNumber(row, col)) {
                        number += input[row][col]
                        colStart = col
                    } else if (isBetweenNumbers(row, col)) {
                        number += input[row][col]
                    } else if (isEndOfNumber(row, col)) {
                        number += input[row][col]
                        partNumbers.add(PartNumber(number, row, colStart, col))
                        number = ""
                    }
                }
            }
        }
    }

    private fun isBetweenNumbers(row: Int, col: Int): Boolean {
        return input[row][col-1].isDigit() && input[row][col+1].isDigit()
    }

    private fun isStartOfNumber(row: Int, col: Int): Boolean {
        return !input[row][col-1].isDigit()
    }

    private fun isEndOfNumber(row: Int, col: Int): Boolean {
        return !input[row][col+1].isDigit()
    }

    private fun isGear(char: Char): Boolean {
        return char == '*'
    }

    class PartNumber(val value: String, val row: Int, val colStart: Int, val colEnd: Int) {
        override fun toString(): String {
            return "PartNumber(value='$value', row=$row, colStart=$colStart, colEnd=$colEnd)"
        }
    }

    class Gear(val row: Int, val col: Int) {
        override fun toString(): String {
            return "Gear(row=$row, col=$col)"
        }
    }
}