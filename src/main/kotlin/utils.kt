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