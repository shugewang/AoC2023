// Day 6
class BoatGame(val input: MutableList<String>) {
    private val timeAndDistanceRecords: MutableMap<Int, Int> = getGameRecords()

    private fun parse(index: Int): List<String> {
        return input[index].split(":")[1].trim().split(" ").filterNot { it == "" }
    }

    private fun getGameRecords(): MutableMap<Int, Int> {
        val timeAndDistanceRecords = mutableMapOf<Int, Int>()
        val times = parse(0)
        val distances = parse(1)
        for (i in times.indices) {
            timeAndDistanceRecords[times[i].toInt()] = distances[i].toInt()
        }
        return timeAndDistanceRecords
    }

    fun multiplyNumberOfWaysToWin(): Int {
        var product = 1
        timeAndDistanceRecords.forEach() {
            product *= findNumberOfWaysToWin(it).size
        }
        return product
    }

    private fun findNumberOfWaysToWin(game: Map.Entry<Int, Int>): MutableList<Int> {
        var holdToWin = mutableListOf<Int>()
        for (second in 1..game.key) {
            if (calculateDistanceTravelled(game.key, second) > game.value) {
                holdToWin.add(second)
            }
        }
        return holdToWin
    }

    private fun calculateDistanceTravelled(time:Int, holdFor: Int): Int {
        return (time - holdFor) * holdFor
    }
}
