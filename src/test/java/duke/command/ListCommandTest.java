package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class ListCommandTest {
    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        Task[] sourceTasks = new Task[] {
            new Todo("Test"),
            new Event("Test", LocalDateTime.parse("2022-12-22T12:00")),
            new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")),
        };
        TaskList list = new TaskList();
        Arrays.stream(sourceTasks).forEachOrdered(list::addTask);

        PrinterStub linePrinter = new PrinterStub();

        new ListCommand("").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());
        assertEquals("1. " + sourceTasks[0].getReadableString(), linePrinter.getLines().get(1));
        assertEquals("2. " + sourceTasks[1].getReadableString(), linePrinter.getLines().get(2));
        assertEquals("3. " + sourceTasks[2].getReadableString(), linePrinter.getLines().get(3));
    }
}
