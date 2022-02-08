package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.testutil.PrinterStub;

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
