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
import duke.task.TaskType;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class CreateCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        PrinterStub linePrinter = new PrinterStub();

        new CreateCommand("test fully 1", TaskType.TODO).execute(linePrinter, taskList);
        assertEquals(1, taskList.getTaskCount());
        assertTrue(taskList.getTaskByIndex(0) instanceof Todo);
        assertEquals("test fully 1", taskList.getTaskByIndex(0).getDescription());

        new CreateCommand("test fully 2 /by 23/12/2022 18:00", TaskType.DEADLINE)
                .execute(linePrinter, taskList);
        assertEquals(2, taskList.getTaskCount());
        assertTrue(taskList.getTaskByIndex(1) instanceof Deadline);
        assertEquals("test fully 2", taskList.getTaskByIndex(1).getDescription());
        assertEquals("2022-12-23T18:00", taskList.getTaskByIndex(1).getDate()
                .map(LocalDateTime::toString).orElse(null));

        new CreateCommand("test fully 3 /at 24/12/2022 18:00", TaskType.EVENT)
                .execute(linePrinter, taskList);
        assertEquals(3, taskList.getTaskCount());
        assertTrue(taskList.getTaskByIndex(2) instanceof Event);
        assertEquals("test fully 3", taskList.getTaskByIndex(2).getDescription());
        assertEquals("2022-12-24T18:00", taskList.getTaskByIndex(2).getDate()
                .map(LocalDateTime::toString).orElse(null));
    }

    @Test
    public void testParsing_emptyTaskName_exceptionRaised() {
        TaskList list = new TaskList();
        PrinterStub linePrinter = new PrinterStub();

        try {
            new CreateCommand("", TaskType.TODO).execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Task description cannot be empty", ex.getMessage());
        }

        try {
            new CreateCommand(" /by 23/12/2022 18:00", TaskType.DEADLINE)
                    .execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Task description cannot be empty", ex.getMessage());
        }

        try {
            new CreateCommand(" /at 23/12/2022 18:00", TaskType.EVENT).execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Task description cannot be empty", ex.getMessage());
        }
    }

    @Test
    public void testParsingDeadline_invalidSyntax_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new CreateCommand("23/12/2022 18:00", TaskType.DEADLINE).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /by <Date>", ex.getMessage());
        }

        try {
            new CreateCommand("/by 23/12/2022 18:00", TaskType.DEADLINE)
                    .execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /by <Date>", ex.getMessage());
        }

        try {
            new CreateCommand("test /by", TaskType.DEADLINE).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /by <Date>", ex.getMessage());
        }
    }

    @Test
    public void testParsingDeadline_invalidDateTime_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new CreateCommand("test /by test", TaskType.DEADLINE).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }

        try {
            new CreateCommand("test /by 32/12/2022 18:00", TaskType.DEADLINE)
                    .execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }
    }

    @Test
    public void testParsingEvent_invalidSyntax_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new CreateCommand("23/12/2022 18:00", TaskType.EVENT).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /at <Date>", ex.getMessage());
        }

        try {
            new CreateCommand("/at 23/12/2022 18:00", TaskType.EVENT).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /at <Date>", ex.getMessage());
        }

        try {
            new CreateCommand("test /at", TaskType.EVENT).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Not in the format <Task name> /at <Date>", ex.getMessage());
        }
    }

    @Test
    public void testParsingEvent_invalidDateTime_exceptionRaised() {
        PrinterStub linePrinter = new PrinterStub();

        try {
            new CreateCommand("test /at test", TaskType.EVENT).execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }

        try {
            new CreateCommand("test /at 32/12/2022 18:00", TaskType.EVENT)
                    .execute(linePrinter, taskList);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("DateTime not in a known format", ex.getMessage());
        }
    }
}
