package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class UpcomingCommandTest {
    private final Task[] sourceTasks = new Task[] {
        new Todo("Test 1"),
        new Event("Test 2", LocalDateTime.now().plusDays(4).minusSeconds(10)),
        new Deadline("Test 3", LocalDateTime.now().plusDays(2).minusSeconds(10)),
        new Deadline("Test 4", LocalDateTime.now().plusDays(3).minusSeconds(10)),
        new Event("Test 5", LocalDateTime.now().plusDays(7).minusSeconds(10)),
        new Event("Test 6", LocalDateTime.now().plusDays(8).minusSeconds(10))
    };
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
        Arrays.stream(sourceTasks).forEachOrdered(taskList::addTask);
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        PrinterStub linePrinter = new PrinterStub();

        new UpcomingCommand("3").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());

        linePrinter.clear();
        new UpcomingCommand("4").execute(linePrinter, taskList);
        assertEquals(4, linePrinter.lineCount());
        assertTrue(linePrinter.getLines().get(1).contains("Test 3"));
        assertTrue(linePrinter.getLines().get(2).contains("Test 4"));
        assertTrue(linePrinter.getLines().get(3).contains("Test 2"));

        linePrinter.clear();
        new UpcomingCommand("1").execute(linePrinter, taskList);
        assertEquals(1, linePrinter.lineCount());

        taskList.markTask(taskList.getTaskByIndex(3), true);
        linePrinter.clear();
        new UpcomingCommand("4").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new UpcomingCommand("test").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Days must be a non-negative number", ex.getMessage());
        }

        try {
            new UpcomingCommand("-1").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Days must be a non-negative number", ex.getMessage());
        }
    }
}
