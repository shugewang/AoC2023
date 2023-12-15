package pipemaze

import org.junit.jupiter.api.Assertions.*
import parseInputIntoListOfString
import kotlin.test.Test

class PipeMazeTest {
    @Test
    fun checkConnectionsTest() {
        val pipeMaze = PipeMaze(parseInputIntoListOfString("src/main/resources/pipe_sketch.txt"))
        val expected = true
        assertEquals(expected, pipeMaze.isValidConnection('|', '|',"right"))
    }


}