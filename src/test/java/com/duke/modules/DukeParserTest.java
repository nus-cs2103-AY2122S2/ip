package com.duke.modules;

import com.duke.command.CommandResult;
import com.duke.modules.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
