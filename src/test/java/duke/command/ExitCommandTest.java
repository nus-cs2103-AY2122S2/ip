package duke.command;

import duke.task.TaskList;
import duke.testutil.PrinterStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExitCommandTest {
    @Test
    public void testParsing_valid_success() {
        TaskList list = new TaskList();
        PrinterStub linePrinter = new PrinterStub();

        assertFalse(new ExitCommand("").execute(linePrinter, list));
        assertEquals(1, linePrinter.lineCount());
        assertEquals("Goodbye! Have a Nice Day.", linePrinter.getLines().get(0));
    }
}
