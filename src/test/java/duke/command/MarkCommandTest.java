package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {
    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        Task[] sourceTasks = new Task[] {
                new Todo("Test"),
                new Event("Test", LocalDateTime.parse("2022-12-22T12:00")),
                new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")),
                new Deadline("Test", LocalDateTime.parse("2022-12-22T14:00")),
                new Event("Test", LocalDateTime.parse("2022-12-23T15:00")),
                new Event("Test", LocalDateTime.parse("2022-12-24T16:00"))
        };
        TaskList list = new TaskList();
        Arrays.stream(sourceTasks).forEachOrdered(list::addTask);

        PrinterStub linePrinter = new PrinterStub();

        assertFalse(sourceTasks[0].isDone());
        new MarkCommand("1", true).execute(linePrinter, list);
        assertTrue(sourceTasks[0].isDone());
        assertEquals("\t " + sourceTasks[0].getReadableString(), linePrinter.getLines().get(1));

        linePrinter.clear();
        new MarkCommand("5", true).execute(linePrinter, list);
        assertTrue(sourceTasks[0].isDone());
        assertEquals("\t " + sourceTasks[4].getReadableString(), linePrinter.getLines().get(1));

        linePrinter.clear();
        new MarkCommand("1", false).execute(linePrinter, list);
        assertFalse(sourceTasks[0].isDone());
        assertEquals("\t " + sourceTasks[0].getReadableString(), linePrinter.getLines().get(1));
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Test"));
        PrinterStub linePrinter = new PrinterStub();

        try {
            new MarkCommand("test", true).execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Task Number must be a number", ex.getMessage());
        }

        try {
            new MarkCommand("0", true).execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("No matching task with given number", ex.getMessage());
        }

        try {
            new MarkCommand("2", true).execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("No matching task with given number", ex.getMessage());
        }
    }
}
