class CubeConundrum(private val input: MutableList<String>) {
    private var games = mutableListOf<Game>()
    private val requirement = mutableMapOf<String, Int>("red" to 12, "blue" to 14, "green" to 13)

    init {
        getGames()
    }

    private fun getGames() {
        input.map {
            games.add(Game(it, requirement))
        }
    }

    fun addUpPossibleIds(): Int {
        var sum = 0
        for (game in games) {
            if (game.isPossible) {
                sum += game.id
            }
        }
        return sum
    }

    class Game(private val game: String, private val requirement: MutableMap<String, Int>) {
        val id = game.split(": ")[0].split(" ")[1].toInt()
        private val sets = mutableListOf<GameSet>()
        var isPossible: Boolean = true

        init {
            getSets()
            checkIfPossible()
        }

        private fun getSets() {
            game.split(": ")[1].split("; ").map {
                sets.add(GameSet(it))
            }
        }

        private fun checkIfPossible() {
            for (set in sets) {
                for (colour in requirement.entries) {
                    if (set.statsMap[colour.key]!! > colour.value) {
                        isPossible = false
                    }
                }
            }
        }

        override fun toString(): String {
            return "Game(id=$id, sets=$sets, isPossible=$isPossible)"
        }
    }

    class GameSet(val set: String) {
        var statsMap = mutableMapOf<String, Int>("red" to 0, "blue" to 0, "green" to 0)

        init {
            getStatFromSet()
        }

        private fun getStatFromSet() {
            for (colour in statsMap.entries) {
                set.split(", ").map {
                    if (it.contains(colour.key)) {
                        statsMap[colour.key] = it.split(" ")[0].toInt()
                    }
                }
            }
        }

        override fun toString(): String {
            return "(statsMap=$statsMap)"
        }
    }
}

