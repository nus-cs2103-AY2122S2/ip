package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BetweenCommandTest {
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

        new BetweenCommand("22/12/2022 10:00 and 22/12/2022 13:30").execute(linePrinter, list);
        assertEquals(3, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("22/12/2022 10:00 and 22/12/2022 14:30").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("22/12/2022 13:30 and 27/12/2022 14:30").execute(linePrinter, list);
        assertEquals(4, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("23/12/2022 13:30 and 23/12/2022 15:30").execute(linePrinter, list);
        assertEquals(2, linePrinter.lineCount());

        linePrinter.clear();
        new BetweenCommand("12/12/2022 13:30 and 13/12/2022 15:30").execute(linePrinter, list);
        assertEquals(1, linePrinter.lineCount());
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        TaskList list = new TaskList();
        PrinterStub linePrinter = new PrinterStub();

        try {
            new BetweenCommand("nothing").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 10:00 22/12/2022 14:30").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 10:00 and").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <date> and <date>", ex.getMessage());
        }

        try {
            new BetweenCommand("www and 22/12/2022 14:30").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in the format dd/MM/yyyy HH:mm", ex.getMessage());
        }

        try {
            new BetweenCommand("22/12/2022 14:30 and asd").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in the format dd/MM/yyyy HH:mm", ex.getMessage());
        }
    }
}
