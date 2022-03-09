package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.Ui;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class MarkCommandTest {
    @Test
    public void markCommand_emptyTask_exceptionThrown() {
        try {
            new MarkCommand("", true);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Task to mark cannot be empty:(", e.getMessage());
        }
    }

    @Test
    public void unmarkCommand_emptyTask_exceptionThrown() {
        try {
            new MarkCommand("", false);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Task to unmark cannot be empty:(", e.getMessage());
        }
    }

    @Test
    public void markCommand_invalidNumber_exceptionThrown() {
        try {
            new MarkCommand("invalid", true);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                            + "please select a valid task to mark using the task's number",
                    e.getMessage());
        }
    }

    @Test
    public void unmarkCommand_invalidNumber_exceptionThrown() {
        try {
            new MarkCommand("invalid", false);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                            + "please select a valid task to unmark using the task's number",
                    e.getMessage());
        }
    }

    @Test
    public void markCommand_numberOutOfRange_exceptionThrown() {
        Ui ui = new Ui();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Test task"));
        try {
            new MarkCommand("8", true).execute(taskList, ui);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                            + "please select a valid task to mark using the task's number",
                    e.getMessage());
        }

        try {
            new MarkCommand("-1", true).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to mark using the task's number", e.getMessage());
        }
    }

    @Test
    public void unmarkCommand_numberOutOfRange_exceptionThrown() {
        Ui ui = new Ui();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Test task"));
        try {
            new MarkCommand("8", false).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to unmark using the task's number", e.getMessage());
        }

        try {
            new MarkCommand("-1", false).execute(taskList, ui);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to unmark using the task's number", e.getMessage());
        }
    }

    @Test
    public void markCommand_valid_success() throws DukeException, IOException {
        Ui ui = new Ui();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Task 1: Todo"));
        taskList.add(new DeadlineTask("Task 2: Deadline w/ Date and Time", "2022-01-01", "11:11"));
        taskList.add(new DeadlineTask("Task 3: Deadline w/ Date only", "2022-01-01"));
        taskList.add(new EventTask("Task 4: Event w/ Date and Time", "2022-01-01", "11:11"));
        taskList.add(new EventTask("Task 5. Event w/ Date", "2022-01-01"));

        String lines = new MarkCommand("1", true).execute(taskList, ui);
        assertEquals("Nice! I've marked this task as done:\n"
                + "       [T][X] Task 1: Todo", lines);
        lines = new MarkCommand("2", true).execute(taskList, ui);
        assertEquals("Nice! I've marked this task as done:\n"
                + "       [D][X] Task 2: Deadline w/ Date and Time (by: 2022-01-01 11:11)", lines);
        lines = new MarkCommand("3", true).execute(taskList, ui);
        assertEquals("Nice! I've marked this task as done:\n"
                + "       [D][X] Task 3: Deadline w/ Date only (by: 2022-01-01)", lines);
        lines = new MarkCommand("4", true).execute(taskList, ui);
        assertEquals("Nice! I've marked this task as done:\n"
                + "       [E][X] Task 4: Event w/ Date and Time (at: 2022-01-01 11:11)", lines);
        lines = new MarkCommand("5", true).execute(taskList, ui);
        assertEquals("Nice! I've marked this task as done:\n"
                + "       [E][X] Task 5. Event w/ Date (at: 2022-01-01)", lines);
    }

    @Test
    public void unmarkCommand_valid_success() throws DukeException, IOException {
        Ui ui = new Ui();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Task 1: Todo", true));
        taskList.add(new DeadlineTask("Task 2: Deadline w/ Date and Time", true, "2022-01-01", "11:11"));
        taskList.add(new DeadlineTask("Task 3: Deadline w/ Date only", true, "2022-01-01"));
        taskList.add(new EventTask("Task 4: Event w/ Date and Time", true, "2022-01-01", "11:11"));
        taskList.add(new EventTask("Task 5. Event w/ Date", true, "2022-01-01"));

        String lines = new MarkCommand("1", false).execute(taskList, ui);
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "       [T][ ] Task 1: Todo", lines);
        lines = new MarkCommand("2", false).execute(taskList, ui);
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "       [D][ ] Task 2: Deadline w/ Date and Time (by: 2022-01-01 11:11)", lines);
        lines = new MarkCommand("3", false).execute(taskList, ui);
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "       [D][ ] Task 3: Deadline w/ Date only (by: 2022-01-01)", lines);
        lines = new MarkCommand("4", false).execute(taskList, ui);
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "       [E][ ] Task 4: Event w/ Date and Time (at: 2022-01-01 11:11)", lines);
        lines = new MarkCommand("5", false).execute(taskList, ui);
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "       [E][ ] Task 5. Event w/ Date (at: 2022-01-01)", lines);
    }
}
