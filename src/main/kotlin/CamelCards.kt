// Day 7
class CamelCards(private val input: MutableList<String>) {
    private val handsAndBids = mutableMapOf<String, Int>()
    private val cardRanking = mutableMapOf<Char, Int>(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'J' to 10,
        'T' to 9,
        '9' to 8,
        '8' to 7,
        '7' to 6,
        '6' to 5,
        '5' to 4,
        '4' to 3,
        '3' to 2,
        '2' to 1
    )
    private val handRanking = mutableMapOf<String, Int>(
        "five of a kind" to 7,
        "four of a kind" to 6,
        "full house" to 5,
        "three of a kind" to 4,
        "two pairs" to 3,
        "one pair" to 2,
        "high card" to 1
    )
    private var order = mutableMapOf<String, MutableMap<String, Int>>()

    var fiveOfAKinds = mutableMapOf<String, Int>()
    var fourOfAKinds = mutableMapOf<String, Int>()
    var fullHouses = mutableMapOf<String, Int>()
    var threeOfAKinds = mutableMapOf<String, Int>()
    var twoPairs = mutableMapOf<String, Int>()
    var onePairs = mutableMapOf<String, Int>()
    var highCards = mutableMapOf<String, Int>()

    init {
        parse()
        identifyAllHands()
        orderWithinHandType()
    }

    private fun parse() {
        input.map { val splitStrings = it.split(" "); handsAndBids[splitStrings[0]] = splitStrings[1].toInt() }
    }

    private fun identifyAllHands() {
        handsAndBids.map { identifyHand(it.key) }
        order = mutableMapOf(
            "five of a kind" to fiveOfAKinds,
            "four of a kind" to fourOfAKinds,
            "full house" to fullHouses,
            "three of a kind" to threeOfAKinds,
            "two pairs" to twoPairs,
            "one pair" to onePairs,
            "high card" to highCards
        )
    }

    private fun orderWithinHandType() {
        for (hand in order.keys) {
            if (order[hand]!!.size > 1) {
                for (position in 4 downTo 0) {
                    if (position == 0 || order[hand]!!.map { it.key[position - 1] }.distinct().size > 1) {
                        order[hand] = orderByPosition(order[hand]!!, position)
                    }
                }
            }
        }
    }

    fun getTotalWinnings(): Int {
        val flattenedOrder = order.values.map { it.values }.flatten().reversed()
        var sum = 0
        for (i in flattenedOrder.indices) sum += (i + 1) * flattenedOrder[i]
        return sum
    }

    private fun orderByPosition(handsOfType: MutableMap<String, Int>, position: Int): MutableMap<String, Int> {
        var newOrder = mutableMapOf<String, Int>()
        handsOfType.keys.sortedByDescending { cardRanking[it[position]] }.map { newOrder[it] = handsOfType[it]!! }
        return newOrder
    }

    private fun identifyHand(hand: String) {
        val numberOfDuplicateCards = getDuplicates(hand)

        if (numberOfDuplicateCards.containsValue(5)) {
            fiveOfAKinds[hand] = handsAndBids[hand]!!
        } else if (numberOfDuplicateCards.containsValue(4)) {
            fourOfAKinds[hand] = handsAndBids[hand]!!
        } else if (numberOfDuplicateCards.containsValue(3) && numberOfDuplicateCards.containsValue(2)) {
            fullHouses[hand] = handsAndBids[hand]!!
        } else if (numberOfDuplicateCards.containsValue(3)) {
            threeOfAKinds[hand] = handsAndBids[hand]!!
        } else if (numberOfDuplicateCards.values.count { it == 2 } == 2) {
            twoPairs[hand] = handsAndBids[hand]!!
        } else if (numberOfDuplicateCards.containsValue(2)) {
            onePairs[hand] = handsAndBids[hand]!!
        } else {
            highCards[hand] = handsAndBids[hand]!!
        }
    }

    private fun getDuplicates(hand: String): MutableMap<Char, Int> {
        var numberOfCards = mutableMapOf<Char, Int>()
        hand.map { numberOfCards[it] = findNumberOfDuplicates(hand, it) }
        return numberOfCards
    }

    private fun findNumberOfDuplicates(cards: String, card: Char): Int {
        return cards.count { it == card }
    }

}
