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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        Task[] taskSource = new Task[] {
                new Todo("Test"),
                new Event("Test", LocalDateTime.parse("2022-12-22T12:00")),
                new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")),
        };
        TaskList list = new TaskList();
        for (Task task : taskSource) {
            list.addTask(task);
        }

        PrinterStub linePrinter = new PrinterStub();

        new ListCommand("").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());
        assertEquals("1. " + taskSource[0].getReadableString(), linePrinter.getLines().get(1));
        assertEquals("2. " + taskSource[1].getReadableString(), linePrinter.getLines().get(2));
        assertEquals("3. " + taskSource[2].getReadableString(), linePrinter.getLines().get(3));
    }
}
