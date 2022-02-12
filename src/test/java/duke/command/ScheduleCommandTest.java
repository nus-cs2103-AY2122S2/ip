package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class ScheduleCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test"));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-22T13:00")));
        taskList.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T12:00")));
        taskList.addTask(new Deadline("Test", LocalDateTime.parse("2022-12-22T14:00")));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-23T15:00")));
        taskList.addTask(new Event("Test", LocalDateTime.parse("2022-12-24T16:00")));
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {


        PrinterStub linePrinter = new PrinterStub();

        new ScheduleCommand("22/12/2022").execute(linePrinter, taskList);
        assertEquals(4, linePrinter.lineCount());

        // Note that the times printed are in 12-hour format instead of 24-hour.
        assertTrue(linePrinter.getLines().get(1).contains("12:00"));
        assertTrue(linePrinter.getLines().get(2).contains("01:00"));
        assertTrue(linePrinter.getLines().get(3).contains("02:00"));

        linePrinter.clear();
        new ScheduleCommand("23/12/2022").execute(linePrinter, taskList);
        assertEquals(2, linePrinter.lineCount());

        linePrinter.clear();
        new ScheduleCommand("25/12/2022").execute(linePrinter, taskList);
        assertEquals("You have no tasks on 25/12/2022!", linePrinter.getLines().get(0));
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new ScheduleCommand("test").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in a known format", ex.getMessage());
        }

        try {
            new ScheduleCommand("32/12/2022").execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Date not in a known format", ex.getMessage());
        }
    }
}
