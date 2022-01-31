package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EmptyDescriptionExceptionTest {
    @Test
    void correctError() {
        EmptyDescriptionException e = new EmptyDescriptionException("todo");
        assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
    }
}
