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
import static org.junit.jupiter.api.Assertions.fail;

public class ExitCommandTest {
    @Test
    public void testParsing_valid_success() {
        TaskList list = new TaskList();
        PrinterStub linePrinter = new PrinterStub();

        new ExitCommand("").execute(linePrinter, list);
        assertEquals(1, linePrinter.lineCount());
        assertEquals("Goodbye! Have a Nice Day.", linePrinter.getLines().get(0));
    }
}
