package taskie.parser;

import taskie.command.AddCommand;
import taskie.command.ExitCommand;
import taskie.command.ListCommand;
import taskie.command.MarkCommand;
import taskie.command.UnknownCommand;
import taskie.command.UnmarkCommand;
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