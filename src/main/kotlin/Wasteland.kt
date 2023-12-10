// Day 8
class Wasteland(private val input: MutableList<MutableList<String>>) {
    private val navigation = input[0][0].split("").drop(1).dropLast(1)
    private var network = mutableMapOf<String, List<String>>()
    private var startNodes = listOf<String>()
    private var endNodes = listOf<String>()
    var ghostlySteps = mutableMapOf<String, Int>()


    init {
        parseNetwork()
        findStartingNodes()
        ghostlyNavigate()
    }

    private fun parseNetwork() {
        input[1].map {
            val node = it.split(" = ")[0];
            val connections = it.split(" = ")[1].drop(1).dropLast(1).split(", ");
            network[node] = connections
        }
    }

    private fun ghostlyNavigate() {
        for (node in startNodes) ghostlySteps[node] = 0
        for (startingNode in startNodes) {
            navigate(startingNode)
        }
    }

    private fun navigate(startingNode: String) {
        var route = mutableListOf(startingNode)
        while (!doesContainEndNode(route)) {
            for (direction in navigation) {
                val nextNode = getNextNode(direction, network[route.last()]!!)
                route.add(nextNode)
                if (nextNode.last() == 'Z') break
            }
        }
        ghostlySteps[startingNode] = route.size-1
    }

    private fun doesContainEndNode(route: List<String>): Boolean {
        for (node in endNodes) {
            if (route.contains(node)) return true
        }
        return false
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if ((lcm % a).toInt() == 0 && (lcm % b).toInt() == 0) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }

    private fun getNextNode(direction: String, connections: List<String>): String {
        if (direction == "L") {
            return connections[0]
        } else {
            return connections[1]
        }
    }

    private fun findStartingNodes() {
        startNodes = network.keys.filter { it.last() == 'A' }
        endNodes = network.values.flatten().filter { it.last() == 'Z' }
    }
}
