class Engine (var input: MutableList<String>) {
    var replacePartWithX = mutableListOf<String>()

    init {
        padWithDots()
        checkAll()
        println("answer: " + addUpPartNumbers())
    }

    private fun padWithDots() {
        input = input.map { ".$it." }.toMutableList()
        input.add(0, ".".repeat(input[0].length))
        input.add(".".repeat(input[0].length))
        replacePartWithX = input.toMutableList()
    }

    fun checkAll() {
        for (row in replacePartWithX.indices) {
            for (col in replacePartWithX[row].indices) {
                if (replacePartWithX[row][col].isDigit()) {
                    if (checkIfAdjacentToSymbol(row, col)) {
                        replacePartWithX[row] = replacePartWithX[row].slice(0..<col) + "X" + replacePartWithX[row].slice(col+1..<replacePartWithX[row].length)
                    }
                }
            }
        }
    }
    private fun checkIfAdjacentToSymbol(row: Int, col: Int): Boolean {
        return isSymbol(replacePartWithX[row - 1][col]) || isSymbol(replacePartWithX[row + 1][col]) || isSymbol(replacePartWithX[row][col - 1]) || isSymbol(replacePartWithX[row][col + 1]) ||
                isSymbol(replacePartWithX[row - 1][col - 1]) || isSymbol(replacePartWithX[row - 1][col + 1]) || isSymbol(replacePartWithX[row + 1][col - 1]) || isSymbol(replacePartWithX[row + 1][col + 1])
    }

    private fun isSymbol(char: Char): Boolean {
        return !char.isDigit() && char != '.' && char != 'X'
    }

    fun keepOnlyNumbers(toParse: MutableList<String>): MutableList<String> {
        return toParse.map { it.split(".", "*", "#", "%", "-","/", "@","$","&","=","+")}.map { it.filter { !it.contains('X') } }.map { it.filter { it.isNotEmpty() } }.flatten().filter { it.all { it.isDigit()} }
            .toMutableList()
    }

    fun addUpPartNumbers(): Int{
        val allNumbers = keepOnlyNumbers(input)
        val onlyNonePartNumbers = keepOnlyNumbers(replacePartWithX)
        println(allNumbers)
        println(onlyNonePartNumbers)
        return allNumbers.sumOf { it.toInt() } - onlyNonePartNumbers.sumOf { it.toInt() }
    }

    fun getAllPossibleSymbols() {
        val allSymbols = input.map { it.filter { !it.isDigit() }}.flatMap { it.toList() }.filter { it != '.' }.toSet()
        println(allSymbols)
    }
}