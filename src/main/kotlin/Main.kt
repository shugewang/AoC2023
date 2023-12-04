import java.io.File
import java.io.InputStream

fun parseInputIntoListOfString(filePath: String): MutableList<String> {
    val inputStream: InputStream = File(filePath).inputStream()
    val listOfString = mutableListOf<String>()
    for (line in inputStream.bufferedReader().lines()) {
        listOfString.add(line)
    }
    return listOfString
}

fun main() {
    // Day 1
//    val calibration = Calibration(processInputIntoListOfString("src/main/resources/calibration_document.txt"))
//    println("Day 1 solution: " + calibration.addUpCalibratedValues())

    // Day 2
//    val cubeConundrum = CubeConundrum(processInputIntoListOfString("src/main/resources/cube_game.txt"))
//    println("Day 2 Part 1 solution: " + cubeConundrum.addUpPossibleGameIds())
//    println("Day 2 Part 2 solution: " + cubeConundrum.addUpAllPowers())

    // Day 3
//    val engineSchematic = Engine(parseInputIntoListOfString("src/main/resources/engine_schematic.txt"))
//    println("Day 3 Part 2 solution: " + engineSchematic.getGearRatios())

    // Day 4
    val scratchcard = Scratchcards(parseInputIntoListOfString(("src/main/resources/scratchcard.txt")))
    println("Day 4 Part 1 solution: " + scratchcard.addUpPoints())
    println("Day 4 Part 2 solution: " + scratchcard.getTotalCards())
}