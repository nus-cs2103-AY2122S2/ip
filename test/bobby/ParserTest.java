package bobby;

import bobby.command.ByeCommand;
import bobby.command.DeadlineCommand;
import bobby.command.DeleteCommand;
import bobby.command.EventCommand;
import bobby.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParserTest {

    @Test
    public void parse_byeCommand() {
        assertEquals(new ByeCommand(), Parser.parse("bye"));
        assertEquals(new ByeCommand(), Parser.parse("BYE"));
        assertEquals(new ByeCommand(), Parser.parse("BYE"));
        assertNotEquals(new ByeCommand(), Parser.parse("|3YE"));
    }

    @Test
    public void parse_listCommand() {
        assertEquals(new ListCommand(), Parser.parse("list"));
        assertEquals(new ListCommand(), Parser.parse("lIsT"));
        assertEquals(new ListCommand(), Parser.parse("LIST"));
        assertNotEquals(new ListCommand(), Parser.parse("L1ST"));
    }

    @Test
    public void parse_deadlineCommand() {
        String command1 = "deadline something / 01-02-2022";
        String command2 = "dEaDLiNe something / 01-02-2022";
        assertEquals(new DeadlineCommand(command1), Parser.parse(command1));
        assertEquals(new DeadlineCommand(command1), Parser.parse(command2));
        assertEquals(new DeadlineCommand(command1), Parser.parse("DeAdLIne"));
        assertNotEquals(new DeadlineCommand(command1), Parser.parse("d34dLine"));
    }

    @Test
    public void parse_deleteCommand() {
        String command1 = "delete 1";
        String[] command1Arr = command1.split(" ");
        assertEquals(new DeleteCommand(command1, command1Arr), Parser.parse("delete"));
        assertEquals(new DeleteCommand(command1, command1Arr), Parser.parse("DELETE"));
        assertNotEquals(new DeleteCommand(command1, command1Arr), Parser.parse("dElEt3"));
    }

    @Test
    public void parse_eventCommand() {
        String command1 = "event something / 01-02-2022";
        String command2 = "EVeNt something / 01-02-2022";
        assertEquals(new EventCommand(command1), Parser.parse(command1));
        assertEquals(new EventCommand(command1), Parser.parse(command2));
        assertEquals(new EventCommand(command1), Parser.parse("evENt"));
        assertNotEquals(new EventCommand(command1), Parser.parse("eV3nt"));
    }
}