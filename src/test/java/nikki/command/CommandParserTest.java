package nikki.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import nikki.NikkiException;

public class CommandParserTest {
    @Test
    public void parseCommand() {
        CommandParser parser = new CommandParser();

        try {
            Command cmd;

            cmd = parser.parseCommand("list");
            assertEquals(cmd.getName(), "list");
            assertEquals(cmd.getArgs(), "");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.parseCommand("event wife's birthday /at 13/12/2022");
            assertEquals(cmd.getName(), "event");
            assertEquals(cmd.getArgs(), "wife's birthday");
            assertEquals(cmd.getKwargs().size(), 1);

            cmd = parser.parseCommand("todo groceries");
            assertEquals(cmd.getName(), "todo");
            assertEquals(cmd.getArgs(), "groceries");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.parseCommand("delete 2");
            assertEquals(cmd.getName(), "delete");
            assertEquals(cmd.getArgs(), "2");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.parseCommand("unmark 3");
            assertEquals(cmd.getName(), "unmark");
            assertEquals(cmd.getArgs(), "3");
            assertEquals(cmd.getKwargs().size(), 0);
        } catch (NikkiException e) {
            // Shouldn't happen
            assert false;
        }
    }

    @Test
    public void readAndParse_invalidCommand_exceptionThrown() {
        String input = "deadline ahhh /by Sunday";
        CommandParser parser = new CommandParser();

        assertThrows(NikkiException.class, () -> parser.parseCommand(input));
    }
}
