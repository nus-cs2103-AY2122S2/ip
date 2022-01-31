package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test EmptyDescription exception
 */
public class EmptyDescriptionExceptionTest {

    /**
     * Tests correct error message is returned
     */
    @Test
    void correctError() {
        EmptyDescriptionException e = new EmptyDescriptionException("todo");
        assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
    }
}
