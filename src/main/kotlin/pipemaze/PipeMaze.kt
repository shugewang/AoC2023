package pipemaze

class PipeMaze(var input: MutableList<String>) {

    val validConnections = mapOf('|' to listOf(Pair("above", '|'), Pair("below", '|')))

    fun isValidConnection(pipe1: Char, pipe2: Char, positioning: String): Boolean {
        if (validConnections[pipe1]!!.contains(Pair(positioning, pipe2))) {
            return true
        }
        return false
    }

}

