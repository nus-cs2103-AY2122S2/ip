package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.IncorrectValueException;
import org.junit.jupiter.api.Test;

/**
 * Class to test IncorrectValue exception
 */
public class IncorrectValueExceptionTest {

    /**
     * Tests correct error message is returned
     */
    @Test
    void correctError() {
        IncorrectValueException e = new IncorrectValueException();
        assertEquals("☹ OOPS!!! The value input is incorrect", e.getMessage());
    }
}
