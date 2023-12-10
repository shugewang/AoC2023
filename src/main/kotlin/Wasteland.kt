// Day 8
class Wasteland(private val input: MutableList<MutableList<String>>) {
    private val navigation = input[0][0].split("").drop(1).dropLast(1)
    private var network = mutableMapOf<String, List<String>>()
    private var route = mutableListOf<String>()

    init {
        parseNetwork()
        navigate()
    }

    private fun parseNetwork() {
        input[1].map {
            val node = it.split(" = ")[0];
            val connections = it.split(" = ")[1].drop(1).dropLast(1).split(", ");
            network[node] = connections
        }
    }

    private fun navigate() {
        route = mutableListOf("AAA")
        while (!route.contains("ZZZ")) {
            for (direction in navigation) {
                val nextNode = getNextNode(direction, network[route.last()]!!)
                route.add(nextNode)
            }
        }
    }

    fun getSteps(): Int {
        return route.size - 1
    }

    private fun getNextNode(direction: String, connections: List<String>): String {
        if (direction == "L") {
            return connections[0]
        } else {
            return connections[1]
        }
    }
}
