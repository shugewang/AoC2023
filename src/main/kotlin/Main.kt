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
    val calibration = Calibration(processInputIntoListOfString("src/main/resources/calibration_document.txt"))
    println(calibration.addAllCalibrationValues())
}