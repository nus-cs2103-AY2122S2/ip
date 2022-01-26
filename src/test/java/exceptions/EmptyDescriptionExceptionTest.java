package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyDescriptionExceptionTest {
	@Test
	void correctError() {
		EmptyDescriptionException e = new EmptyDescriptionException("todo");
		assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
	}
}
