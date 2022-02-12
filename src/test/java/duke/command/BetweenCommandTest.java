package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class BetweenCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test"));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-22T12:00")));
        taskList.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")));
        taskList.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T14:00")));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-23T15:00")));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-24T16:00")));
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        PrinterStub linePrinter = new PrinterStub();

        new BetweenCommand("22/12/2022 10:00 and 22/12/2022 13:30").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("22/12/2022 10:00 and 22/12/2022 14:30").execute(linePrinter, taskList);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("22/12/2022 13:30 and 27/12/2022 14:30").execute(linePrinter, taskList);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("23/12/2022 13:30 and 23/12/2022 15:30").execute(linePrinter, taskList);
        assertEquals(2, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("12/12/2022 13:30 and 13/12/2022 15:30").execute(linePrinter, taskList);
        assertEquals(1, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidSyntax_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new BetweenCommand("nothing").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 10:00 22/12/2022 14:30").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 10:00 and").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }
    }

    @Test
    public void testParsing_invalidDateTime_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new BetweenCommand("www and 22/12/2022 14:30").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 14:30 and asd").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }
    }
}
