package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {
    @Test
    public void parse_todoValidInput_success() {
        Command c = duke.command.Parser.parse("todo /desc todoDescription");
        assertEquals(c.getType(), CommandType.ADD_TODO);
        assertEquals(c.getParams().get("desc"), "todoDescription");
    }

    @Test
    public void parse_deadlineValidInput_success() {
        Command c = duke.command.Parser.parse("deadline /desc deadlineDescription /by 2022-1-1 1230");
        assertEquals(c.getType(), CommandType.ADD_DEADLINE);
        assertEquals(c.getParams().get("desc"), "deadlineDescription");
        assertEquals(c.getParams().get("by"), "2022-1-1 1230");
    }

    @Test
    public void parse_eventValidInput_success() {
        Command c = duke.command.Parser.parse("event /desc eventDescription /at 2022-1-1 1230 /dur 1h15m");
        assertEquals(c.getType(), CommandType.ADD_EVENT);
        assertEquals(c.getParams().get("desc"), "eventDescription");
        assertEquals(c.getParams().get("at"), "2022-1-1 1230");
        assertEquals(c.getParams().get("dur"), "1h15m");
    }

    @Test
    public void parse_todoInvalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("todo"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("todo /desc"));
    }

    @Test
    public void parse_deadlineInvalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("deadline"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("deadline /desc"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("deadline /desc deadlineDescription"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("deadline /desc deadlineDescription /by"));
    }

    @Test
    public void parse_eventInvalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("event"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("event /desc"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("event /desc eventDescription"));
        assertThrows(DukeException.class, () -> duke.command.Parser.parse("event /desc eventDescription /at"));
        assertThrows(DukeException.class, () -> duke.command.Parser
                .parse("event /desc eventDescription /at 2022-1-1 1230"));
        assertThrows(DukeException.class, () -> duke.command.Parser
                .parse("event /desc eventDescription /at 2022-1-1 1230 /dur"));
    }
}
