package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
        assertEquals(AddCommand.class, Parser.parse("todo test").getClass());
        assertEquals(AddCommand.class, Parser.parse("event test /at 2020/12/12").getClass());
        assertEquals(AddCommand.class, Parser.parse("deadline test /by 2020/12/12").getClass());
        assertEquals(MarkCommand.class, Parser.parse("mark 1").getClass());
        assertEquals(UnmarkCommand.class, Parser.parse("unmark 1").getClass());
        assertEquals(UnknownCommand.class, Parser.parse("laksjf;mnsfjsflw").getClass());
    }
}