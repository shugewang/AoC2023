// Day 9
class MirageMaintenance(val input: MutableList<String>) {
    private val report = input.map { it.split(" ").map { it.toInt() } }

    init {
        println(report)
        println(processReport())
    }

    private fun processReport(): Int {
        var sum = 0
        for (sequence in report) {
            val sequenceDifferences = mutableListOf<MutableList<Int>>()
            getDifference(sequenceDifferences, sequence)
            findNextValue(sequenceDifferences)
            sum += sequenceDifferences[0].last()
        }
        return sum
    }

    private fun getDifference(sequenceDifferences: MutableList<MutableList<Int>>, sequence: List<Int>): List<Int> {
        return if (sequence[0] == 0 && sequence.distinct().size == 1) {
            sequence
        } else {
            sequenceDifferences.add(sequence.toMutableList())
            val differences = mutableListOf<Int>()
            for (i in 0..<sequence.size - 1) {
                differences.add(sequence[i + 1] - sequence[i])
            }
            getDifference(sequenceDifferences, differences)
        }
    }

    private fun findNextValue(sequenceDifferences: MutableList<MutableList<Int>>) {
        for (i in sequenceDifferences.size-2 downTo 0) {
            sequenceDifferences[i].add(sequenceDifferences[i].last() + sequenceDifferences[i+1].last())
        }
    }
}
