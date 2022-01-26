package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutOfRangeExceptionTest {

	@Test
	void correctError() {
		OutOfRangeException e = new OutOfRangeException();
		assertEquals("☹ OOPS!!! The value input is not in the list", e.getMessage());
	}
}
