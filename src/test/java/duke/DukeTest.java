package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    /*
    Java code normally use camelCase for method names e.g., testStringConversion
    but when writing test methods, sometimes another convention is used:
    whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    e.g., intDivision_zeroDivisor_exceptionThrown

    Tests use Assert.assertEquals(expected, actual) methods to compare the expected output with the actual output.
     */

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
}
