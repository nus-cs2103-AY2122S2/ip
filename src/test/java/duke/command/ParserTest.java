package duke.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void parserTest() {
        TaskList taskList = new TaskList();
        assertEquals(Parser.parseInput("todo test", taskList), "added: [T][ ] test\n"+"You have 1 tasks in your list\n");
    }
}
