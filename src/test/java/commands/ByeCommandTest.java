package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
	ByeCommand cmd;

	@BeforeEach
	void setUp() {
		cmd = new ByeCommand();
	}

	@Test
	void getListBeforeExecute() {
		assertNull(cmd.getList());
	}

	@Test
	void Ends() {
		assertTrue(cmd.ends());
	}
}
