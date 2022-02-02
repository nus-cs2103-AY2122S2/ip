package duke.duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;

import duke.dukeexceptions.DukeException;

import duke.parser.Parser;


public class ParserTest {
    @Test
    public void test_parse() {
        Parser cmdLine = new Parser();
        try {
            assertTrue(cmdLine.getCommand("bye") instanceof ByeCommand);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
