// Day 6
class BoatGame(val input: MutableList<String>) {
    private val timeAndDistanceRecords: MutableMap<Long, Long> = getGameRecords()

    private fun parse(index: Int): List<String> {
        return input[index].split(":")[1].replace(" ", "").split(" ").filterNot { it == "" }
    }

    private fun getGameRecords(): MutableMap<Long, Long> {
        val timeAndDistanceRecords = mutableMapOf<Long, Long>()
        val times = parse(0)
        val distances = parse(1)
        for (i in times.indices) {
            timeAndDistanceRecords[times[i].toLong()] = distances[i].toLong()
        }
        return timeAndDistanceRecords
    }

    fun multiplyNumberOfWaysToWin(): Long {
        var product = 1
        timeAndDistanceRecords.forEach() {
            product *= findNumberOfWaysToWin(it).size
        }
        return product.toLong()
    }

    private fun findNumberOfWaysToWin(game: Map.Entry<Long, Long>): MutableList<Long> {
        var holdToWin = mutableListOf<Long>()
        for (second in 1..game.key) {
            if (calculateDistanceTravelled(game.key, second) > game.value) {
                holdToWin.add(second)
            }
        }
        return holdToWin
    }

    private fun calculateDistanceTravelled(time:Long, holdFor: Long): Long {
        return (time - holdFor) * holdFor
    }
}
