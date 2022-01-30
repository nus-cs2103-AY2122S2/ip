package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class ScheduleCommandTest {
    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        TaskList list = new TaskList();
        list.addTask(new Todo("Test"));
        list.addTask(new Event("Test", LocalDateTime.parse("2022-12-22T12:00")));
        list.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")));
        list.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T14:00")));
        list.addTask(new Event("Test", LocalDateTime.parse("2022-12-23T15:00")));
        list.addTask(new Event("Test", LocalDateTime.parse("2022-12-24T16:00")));

        PrinterStub linePrinter = new PrinterStub();

        new ScheduleCommand("22/12/2022").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new ScheduleCommand("23/12/2022").execute(linePrinter, list);
        assertEquals(2, linePrinter.lineCount());

        linePrinter.clear();
        new ScheduleCommand("25/12/2022").execute(linePrinter, list);
        assertEquals(1, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        TaskList list = new TaskList();
        PrinterStub linePrinter = new PrinterStub();

        try {
            new ScheduleCommand("test").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in the format dd/MM/yyyy HH:mm", ex.getMessage());
        }

        try {
            new ScheduleCommand("32/12/2022").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in the format dd/MM/yyyy HH:mm", ex.getMessage());
        }
    }
}
