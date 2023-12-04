// Day 4 Solution
class Scratchcards (var input: MutableList<String>){
    var scratchcards = mutableListOf<Scratchcard>()

    init {
        getCardInfo()
    }

    private fun getCardInfo() {
        input.map {
            var id = it.split(":")[0].split(" ").last().toInt()
            var winningNumbers = parseString(it.split(":")[1].split("|")[0])
            var yourNumbers = parseString(it.split(":")[1].split("|")[1])
            scratchcards.add(Scratchcard(id, winningNumbers, yourNumbers))
        }
    }

    private fun parseString(input: String): List<Int> {
        return input.split(" ").filter { it.isNotEmpty()}.map {it.toInt()}.toList()
    }

    fun addUpPoints(): Int {
        var totalPoints = 0
        scratchcards.map { totalPoints+=it.point }
        return totalPoints
    }
}

class Scratchcard(var id: Int, var winningNumbers: List<Int>, var yourNumbers: List<Int>){
    private var wins = mutableListOf<Int>()
    var point = 0

    init {
        checkWinningNumbers()
        getPoints()
    }

    override fun toString(): String {
        return "Scratchcard(id=$id, winningNumbers=$winningNumbers, yourNumbers=$yourNumbers)"
    }

    private fun checkWinningNumbers() {
        for (number in winningNumbers) {
            if (yourNumbers.contains(number)) {
                wins.add(number)
            }
        }
    }

    private fun getPoints() {
        if (wins.size == 0) {
            point = 0
        } else {
            point = 1
            for (i in 1..<wins.size) {
                point *= 2
            }
        }
    }
}