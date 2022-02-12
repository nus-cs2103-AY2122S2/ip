package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class FindCommandTest {
    private final Task[] taskSource = new Task[] {
        new Todo("Test 1"),
        new Event("Test 2", LocalDateTime.parse("2022-12-22T12:00")),
        new Deadline("Another OTTER", LocalDateTime.parse("2022-12-22T13:00")),
        new Deadline("Some other stuff", LocalDateTime.parse("2022-12-22T14:00")),
        new Event("Watch the Otter", LocalDateTime.parse("2022-12-23T15:00")),
        new Event("Read Some Book", LocalDateTime.parse("2022-12-24T16:00"))
    };
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
        Arrays.stream(taskSource).forEach(taskList::addTask);
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        PrinterStub linePrinter = new PrinterStub();

        new FindCommand("test").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());
        assertEquals("1. " + taskSource[0].getReadableString(), linePrinter.getLines().get(1));
        assertEquals("2. " + taskSource[1].getReadableString(), linePrinter.getLines().get(2));

        linePrinter.clear();
        new FindCommand("otter").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());
        assertEquals("3. " + taskSource[2].getReadableString(), linePrinter.getLines().get(1));
        assertEquals("5. " + taskSource[4].getReadableString(), linePrinter.getLines().get(2));

        linePrinter.clear();
        new FindCommand("OtTeR").execute(linePrinter, taskList);
        assertEquals(3, linePrinter.lineCount());
        assertEquals("3. " + taskSource[2].getReadableString(), linePrinter.getLines().get(1));
        assertEquals("5. " + taskSource[4].getReadableString(), linePrinter.getLines().get(2));

        linePrinter.clear();
        new FindCommand("t").execute(linePrinter, taskList);
        assertEquals(6, linePrinter.lineCount());

        linePrinter.clear();
        new FindCommand("zzz").execute(linePrinter, taskList);
        assertEquals(1, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new FindCommand("").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Search term cannot be empty", ex.getMessage());
        }
    }
}
