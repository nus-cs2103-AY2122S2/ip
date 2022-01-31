package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IncorrectValueExceptionTest {

    @Test
    void correctError() {
        IncorrectValueException e = new IncorrectValueException();
        assertEquals("☹ OOPS!!! The value input is incorrect", e.getMessage());
    }
}
