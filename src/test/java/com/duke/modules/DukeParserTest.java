package com.duke.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.duke.command.CommandResult;

public class DukeParserTest {
    @Test
    public void byeSingleWordParseTest() {
        Parser testParser = new Parser(null);
        CommandResult testResult = testParser.parse("bye");
        assertEquals(testResult, CommandResult.shutdownResult());
    }

    @Test
    public void byeMultipleWordParseTest() {
        Parser testParser = new Parser(null);
        CommandResult testResult = testParser.parse("bye bot");
        assertNotEquals(testResult, CommandResult.shutdownResult());
    }
}
