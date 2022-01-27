package duke.command;

import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateCommandTest {
    @Test
    public void createCommand_emptyTitle_exceptionThrown() {
        try {
            new CreateCommand("", TaskType.TODO);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The title of a task cannot be empty :(", e.getMessage());
        }
        try {
            new CreateCommand("", TaskType.DEADLINE);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The title of a task cannot be empty :(", e.getMessage());
        }
        try {
            new CreateCommand("", TaskType.EVENT);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The title of a task cannot be empty :(", e.getMessage());
        }
    }

    @Test
    public void createCommand_todoTask_success() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new CreateCommand("Test Title", TaskType.TODO).execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [T][ ] Test Title\n" +
                    "     Now you have 1 task in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    @Test
    public void createCommand_deadlineTask_success() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        //With Date + Time
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new CreateCommand("Test Title /by 2022-01-01 11:11", TaskType.DEADLINE).execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [D][ ] Test Title (by: 2022-01-01 11:11)\n" +
                    "     Now you have 1 task in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);
        } catch (DukeException | IOException e) {
            fail();
        }

        //With Date only
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new CreateCommand("Test Title 2 /by 2022-01-02", TaskType.DEADLINE).execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [D][ ] Test Title 2 (by: 2022-01-02)\n" +
                    "     Now you have 2 tasks in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    @Test
    public void createDeadlineTask_emptyDeadline_exceptionThrown() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        //No /by in the line
        try {
            new CreateCommand("Test title 1", TaskType.DEADLINE).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The deadline cannot be empty :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }

        //Have /by but don't have anything after that
        try {
            new CreateCommand("Test title 2 /by", TaskType.DEADLINE).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The deadline cannot be empty :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void createDeadlineTask_invalidDateTimeFormat_exceptionThrown() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        try {
            new CreateCommand("Test title /by 1234567", TaskType.DEADLINE).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The time is in the wrong format :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }
    }


    @Test
    public void createCommand_eventTask_success() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        //With Date + Time
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new CreateCommand("Test Title /at 2022-01-01 11:11", TaskType.EVENT).execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [E][ ] Test Title (at: 2022-01-01 11:11)\n" +
                    "     Now you have 1 task in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);
        } catch (DukeException | IOException e) {
            fail();
        }

        //With Date only
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new CreateCommand("Test Title 2 /at 2022-01-02", TaskType.EVENT).execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "       [E][ ] Test Title 2 (at: 2022-01-02)\n" +
                    "     Now you have 2 tasks in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    @Test
    public void createEventTask_emptyEventTime_exceptionThrown() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        //No /at in the line
        try {
            new CreateCommand("Test title 1", TaskType.EVENT).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The time of an event cannot be empty :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }

        //Have /at but don't have anything after that
        try {
            new CreateCommand("Test title 2 /at", TaskType.EVENT).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The time of an event cannot be empty :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void createEventTask_invalidDateTimeFormat_exceptionThrown() {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();

        try {
            new CreateCommand("Test title /at 1234567", TaskType.EVENT).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The time is in the wrong format :( "
                    + "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd", e.getMessage());
        }
    }
}
