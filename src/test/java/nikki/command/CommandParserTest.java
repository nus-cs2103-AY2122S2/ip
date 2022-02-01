package nikki.command;

import nikki.NikkiException;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {
    @Test
    public void readAndParse() {
        String input = "list\n"
                + "event wife's birthday /at 13/12/2022\n"
                + "todo groceries\n"
                + "delete 2\n"
                + "unmark 3";

        CommandParser parser = new CommandParser(new Scanner(input));
        try {
            Command cmd;

            cmd = parser.readAndParse();
            assertEquals(cmd.getName(), "list");
            assertEquals(cmd.getArgs(), "");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.readAndParse();
            assertEquals(cmd.getName(), "event");
            assertEquals(cmd.getArgs(), "wife's birthday");
            assertEquals(cmd.getKwargs().size(), 1);

            cmd = parser.readAndParse();
            assertEquals(cmd.getName(), "todo");
            assertEquals(cmd.getArgs(), "groceries");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.readAndParse();
            assertEquals(cmd.getName(), "delete");
            assertEquals(cmd.getArgs(), "2");
            assertEquals(cmd.getKwargs().size(), 0);

            cmd = parser.readAndParse();
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

        CommandParser parser = new CommandParser(new Scanner(input));

        assertThrows(NikkiException.class, () -> parser.readAndParse());
    }
}
