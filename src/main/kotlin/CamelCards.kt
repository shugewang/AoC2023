// Day 7
class CamelCards(private val input: MutableList<String>) {
    private val handsAndBids = mutableMapOf<String, Int>()
    private val cardRanking = mutableMapOf<Char, Int>(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
//        'J' to 10,
        'T' to 9,
        '9' to 8,
        '8' to 7,
        '7' to 6,
        '6' to 5,
        '5' to 4,
        '4' to 3,
        '3' to 2,
        '2' to 1,
        'J' to 0,
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
    private var orderWithJokerRuleApplied = mutableMapOf<String, MutableMap<String, Int>>()


    private var fiveOfAKinds = mutableMapOf<String, Int>()
    private var fourOfAKinds = mutableMapOf<String, Int>()
    private var fullHouses = mutableMapOf<String, Int>()
    private var threeOfAKinds = mutableMapOf<String, Int>()
    private var twoPairs = mutableMapOf<String, Int>()
    private var onePairs = mutableMapOf<String, Int>()
    private var highCards = mutableMapOf<String, Int>()

    init {
        parse()
        identifyAllHands()
        applyJokerRule()
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

    fun applyJokerRule() {
        order.map { hand ->
            hand.value.map {
                upgradeHand(hand.key, it.key)
            }
        }
        var newOrder = mutableMapOf<String, MutableMap<String, Int>>()
        orderWithJokerRuleApplied.keys.sortedByDescending { handRanking[it] }.map { newOrder[it] = orderWithJokerRuleApplied[it]!! }
        order = newOrder
    }

    fun upgradeHand(hand: String, cards: String) {
        if (hand == "high card") {
            if (cards.count { it == 'J' } == 1) {
                upgrade("one pair", cards, hand)
            }
        }
        else if (hand == "one pair") {
            if (cards.count { it == 'J' } == 1) {
                upgrade("three of a kind", cards, hand)
            } else if (cards.count { it == 'J' } == 2) {
                upgrade("three of a kind", cards, hand)
            }
        }
        else if (hand == "two pairs") {
            if (cards.count { it == 'J' } == 1) {
                upgrade("full house", cards, hand)
            } else if (cards.count { it == 'J' } == 2) {
                upgrade("four of a kind", cards, hand)
            } else if (cards.count { it == 'J' } == 3) {
                upgrade("five of a kind", cards, hand)
            }
        }
        else if (hand == "three of a kind") {
            if (cards.count { it == 'J' } == 1) {
                upgrade("four of a kind", cards, hand)
            } else if (cards.count { it == 'J' } == 2) {
                upgrade("five of a kind", cards, hand)
            } else if (cards.count { it == 'J' } == 3) {
                upgrade("four of a kind", cards, hand)
            }
        }
        else if (hand == "four of a kind") {
            if (cards.count { it == 'J' } == 1) {
                upgrade("five of a kind", cards, hand)
            } else if (cards.count { it == 'J' } == 4) {
                upgrade("five of a kind", cards, hand)
            }
        }
        else if (hand == "full house") {
            if (cards.count { it == 'J' } > 0) {
                upgrade("five of a kind", cards, hand)
            }
        }
        if (cards.count { it == 'J' } == 0 || cards.count { it == 'J' } == 5) {
            upgrade(hand, cards, hand)
        }
    }

    private fun upgrade(upgradedHand: String, cards: String, hand: String) {
        if (orderWithJokerRuleApplied[upgradedHand] != null) {
            orderWithJokerRuleApplied[upgradedHand]!![cards] = order[hand]!![cards]!!
        } else {
            orderWithJokerRuleApplied[upgradedHand] = mutableMapOf(cards to order[hand]!![cards]!!)
        }
    }

    fun getKeyByValue(value: Int): String {
        for (hand in handRanking) {
            if (hand.value == value) {
                return hand.key
            }
        }
        return ""
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
