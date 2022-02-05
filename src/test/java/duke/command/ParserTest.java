package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    @Test
    public void parserTest() {
        TaskList taskList = new TaskList();
        assertFalse(Parser.parseInput("bye", taskList));
    }
}
