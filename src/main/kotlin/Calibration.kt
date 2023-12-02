class Calibration (val input: MutableList<String>) {
    private fun recoverCalibration(): List<Int> {
        val calibrationArray = processInputIntoListOfString("src/main/resources/calibration_document.txt")
        val onlyDigits = calibrationArray.map { it -> it.filter { it.isDigit() } }
        return onlyDigits.map { it.first() + it.last().toString() }.map { it.toInt() }
    }

    fun addAllCalibrationValues(): Int {
        return recoverCalibration().sum()
    }
}