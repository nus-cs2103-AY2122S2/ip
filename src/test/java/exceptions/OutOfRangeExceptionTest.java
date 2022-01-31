package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OutOfRangeExceptionTest {

    @Test
    void correctError() {
        OutOfRangeException e = new OutOfRangeException();
        assertEquals("☹ OOPS!!! The value input is not in the list", e.getMessage());
    }
}
