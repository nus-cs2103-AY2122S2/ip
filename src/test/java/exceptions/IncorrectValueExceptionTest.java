package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectValueExceptionTest {

	@Test
	void correctError() {
		IncorrectValueException e = new IncorrectValueException();
		assertEquals("☹ OOPS!!! The value input is incorrect", e.getMessage());
	}
}
