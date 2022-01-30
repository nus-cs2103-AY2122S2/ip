package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class UpcomingCommandTest {
    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        Task[] sourceTasks = new Task[] {
            new Todo("Test"),
            new Event("Test", LocalDateTime.now().plusDays(2).minusSeconds(10)),
            new Deadline("Test", LocalDateTime.now().plusDays(3).minusSeconds(10)),
            new Deadline("Test", LocalDateTime.now().plusDays(4).minusSeconds(10)),
            new Event("Test", LocalDateTime.now().plusDays(7).minusSeconds(10)),
            new Event("Test", LocalDateTime.now().plusDays(8).minusSeconds(10))
        };
        TaskList list = new TaskList();
        Arrays.stream(sourceTasks).forEachOrdered(list::addTask);

        PrinterStub linePrinter = new PrinterStub();

        new UpcomingCommand("3").execute(linePrinter, list);
        assertEquals(3, linePrinter.lineCount());

        linePrinter.clear();
        new UpcomingCommand("4").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new UpcomingCommand("1").execute(linePrinter, list);
        assertEquals(1, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Test"));
        PrinterStub linePrinter = new PrinterStub();

        try {
            new UpcomingCommand("test").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Days must be a positive number", ex.getMessage());
        }

        try {
            new UpcomingCommand("-1").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Days must be a positive number", ex.getMessage());
        }
    }
}
