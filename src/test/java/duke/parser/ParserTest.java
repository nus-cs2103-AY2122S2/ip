package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ListCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void parseTest() {
        // test case 1
        assertEquals(new AddTodoCommand("read books"), parser.parse("todo read books"));

        // test case 2
        assertEquals(new AddEventCommand("tutorial", LocalDate.parse("2022-02-01")),
                parser.parse("event tutorial /at 2022-02-01"));

        // test case 3
        assertEquals(new AddDeadlineCommand("quiz", LocalDate.parse("2022-02-03")),
                parser.parse("deadline quiz /by 2022-02-03"));
    }
}
