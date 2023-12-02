import java.io.File
import java.io.InputStream

fun processInputIntoListOfString(filePath: String): MutableList<String> {
    val inputStream: InputStream = File(filePath).inputStream()
    val listOfString = mutableListOf<String>()
    for (line in inputStream.bufferedReader().lines()) {
        listOfString.add(line)
    }
    return listOfString
}

fun main() {
    // Day 1
    val calibration = Calibration(processInputIntoListOfString("src/main/resources/calibration_document.txt"))
    println("Day 1 solution: " + calibration.addUpCalibratedValues())
    // Day 2
    val cubeConundrum = CubeConundrum(processInputIntoListOfString("src/main/resources/cube_game.txt"))
    println("Day 2 solution: " + cubeConundrum.addUpPossibleIds())
}