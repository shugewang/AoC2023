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

fun parseInputSplitByEmptyLine(filePath: String): MutableList<MutableList<String>> {
    val inputStream: InputStream = File(filePath).inputStream()
    val listOfListOfString = mutableListOf<MutableList<String>>()
    var listOfString = mutableListOf<String>()

    for (line in inputStream.bufferedReader().lines()) {
        if (line.isEmpty()) {
            listOfListOfString.add(listOfString)
            listOfString = mutableListOf()
        } else {
            listOfString.add(line)
        }
    }
    listOfListOfString.add(listOfString)
    return listOfListOfString
}

fun main() {
    // Day 1
//    val calibration = Calibration(parseInputIntoListOfString("src/main/resources/calibration_document.txt"))
//    println("Day 1 solution: " + calibration.addUpCalibratedValues())

    // Day 2
//    val cubeConundrum = CubeConundrum(parseInputIntoListOfString("src/main/resources/cube_game.txt"))
//    println("Day 2 Part 1 solution: " + cubeConundrum.addUpPossibleGameIds())
//    println("Day 2 Part 2 solution: " + cubeConundrum.addUpAllPowers())

    // Day 3
//    val engineSchematic = Engine(parseInputIntoListOfString("src/main/resources/engine_schematic.txt"))
//    println("Day 3 Part 2 solution: " + engineSchematic.getGearRatios())

    // Day 4
//    val scratchcard = Scratchcards(parseInputIntoListOfString(("src/main/resources/scratchcard.txt")))
//    println("Day 4 Part 1 solution: " + scratchcard.addUpPoints())
//    println("Day 4 Part 2 solution: " + scratchcard.getTotalCards())

    // Day 5
//    val foodProduction = Almanac(parseInputSplitByEmptyLine("src/main/resources/almanac.txt"))
//    println("Day 5 Part 1 solution: " + foodProduction.findLowestLocation())

    // Day 6
    val boatGame = BoatGame(parseInputIntoListOfString("src/main/resources/boat_records.txt"))
    println(boatGame.multiplyNumberOfWaysToWin())
}