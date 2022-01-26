package duke.main;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parserTest_validExit_success() {
        try {
            assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse exit command");
        }
    }

    @Test
    void parserTest_validList_success() {
        try {
            assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse list command");
        }
    }

    @Test
    void parserTest_validMark_success() {
        try {
            assertTrue(Parser.parseCommand("mark 1") instanceof MarkCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse mark command");
        }
    }

    @Test
    void parserTest_invalidMark_throwsDukeException() {
        try {
            Parser.parseCommand("mark ");
            Assertions.fail("Parser fails to catch invalid mark command");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.ERROR_PARSE_INT);
        }
    }

    @Test
    void parserTest_validUnmark_success() {
        try {
            Parser.parseCommand("unmark 1");
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse unmark command");
        }
    }

    @Test
    void parsetTest_invalidUnmark_throwsDukeException() {
        try {
            Parser.parseCommand("unmark anything");
            Assertions.fail("Parser fails to catch invalid unmark command");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.ERROR_PARSE_INT);
        }
    }

    @Test
    void parserTest_validDelete_success() {
        try {
            Parser.parseCommand("delete 1");
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse delete command");
        }
    }

    @Test
    void parserTest_invalidDelete_throwsDukeException() {
        try {
            Parser.parseCommand("delete anything");
            Assertions.fail("Parser fails to catch invalid delete command");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.ERROR_PARSE_INT);
        }
    }

    @Test
    void parserTest_validAddTodo_success() {
        try {
            assertTrue(Parser.parseCommand("todo test") instanceof AddTodoCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse todo command");
        }
    }

    @Test
    void parserTest_emptyTodo_throwsDukeException() {
        try {
            Parser.parseCommand("todo ");
            Assertions.fail("Parser fails to catch empty todo command");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.ERROR_TODO_NO_NAME);
        }
    }

    @Test
    void parserTest_validAddDeadline_success() {
        try {
            assertTrue(Parser.parseCommand("deadline test /by 2022/01/26 1200") instanceof AddDeadlineCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse deadline command");
        }
    }

    @Test
    void parserTest_invalidAddDeadline_throwsDukeException() {
        assertAll("Invalid Add Deadline",
                () -> {
                    try {
                        Parser.parseCommand("deadline testDeadline /by /by anything");
                        Assertions.fail("Parser fails to catch deadline command with invalid structure");
                    } catch (DukeException e) {
                        assertEquals(e.getMessage(), DukeException.ERROR_WRONG_FORMAT + "\n"
                                + DukeException.FORMAT_DEADLINE);
                    }
                },
                () -> {
                    try {
                        Parser.parseCommand("deadline testDeadline /by notADate format");
                        Assertions.fail("Parser fails to catch deadline command with invalid date format");
                    } catch (DukeException e) {
                        assertEquals(e.getMessage(), DukeException.FORMAT_DATE);
                    }
                }
        );
    }

    @Test
    void parserTest_validAddEvent_success() {
        try {
            assertTrue(Parser.parseCommand("event test /at 2022/01/26 1200") instanceof AddEventCommand);
        } catch (DukeException e) {
            Assertions.fail("Parser fails to parse event command");
        }
    }

    @Test
    void parserTest_invalidAddEvent_throwsDukeException() {
        assertAll("Invalid Add Event",
                () -> {
                    try {
                        Parser.parseCommand("event /at anything");
                        Assertions.fail("Parser fails to catch event command with invalid structure");
                    } catch (DukeException e) {
                        assertEquals(e.getMessage(), DukeException.ERROR_WRONG_FORMAT + "\n"
                                + DukeException.FORMAT_EVENT);
                    }
                },
                () -> {
                    try {
                        Parser.parseCommand("event testEvent /at notADate format");
                        Assertions.fail("Parser fails to catch event command with invalid date format");
                    } catch (DukeException e) {
                        assertEquals(e.getMessage(), DukeException.FORMAT_DATE);
                    }
                }
        );
    }
}
