package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.OutOfRangeException;
import org.junit.jupiter.api.Test;

/**
 * Class to test EmptyDescription exception
 */
public class OutOfRangeExceptionTest {

    /**
     * Tests correct error message is returned
     */
    @Test
    void correctError() {
        OutOfRangeException e = new OutOfRangeException();
        assertEquals("☹ OOPS!!! The value input is not in the list", e.getMessage());
    }
}
