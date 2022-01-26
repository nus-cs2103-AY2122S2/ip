package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnknownCommandExceptionTest {

	@Test
	void correctError() {
		UnknownCommandException e = new UnknownCommandException();
		assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
	}
}
