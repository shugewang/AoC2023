// Day 4 Solution
class Scratchcards (var input: MutableList<String>){
    var scratchcards = mutableListOf<Scratchcard>()
    var scratchcardsCopies = mutableMapOf<Int, Int>()

    init {
        getCardInfo()
        getInitialNumberOfCards()
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
        scratchcards.map { totalPoints+=it.point; getCardCopies(it.id) }
        return totalPoints
    }

    private fun getInitialNumberOfCards() {
        scratchcards.map { scratchcardsCopies.put(it.id, 1)}
    }

    private fun getCardCopies(card: Int) {
        if (card in scratchcardsCopies.keys) {
            for (number in 1..scratchcardsCopies[card]!!) {
            if (card < scratchcards.size) {
                for (i in 1..scratchcards.filter { it.id == card }[0].numberWins) {
                    if (scratchcardsCopies.contains(card + i)) {
                        scratchcardsCopies[card + i] = scratchcardsCopies.getValue(card + i) + 1
                    } else {
                        scratchcardsCopies.put(card + i, 1)
                    }
                }
            }
            }
        }
    }

    fun getTotalCards(): Int {
        return scratchcardsCopies.values.sum()
    }
}

class Scratchcard(var id: Int, var winningNumbers: List<Int>, var yourNumbers: List<Int>){
    private var wins = mutableListOf<Int>()
    var numberWins = 0
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
        numberWins = wins.size
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