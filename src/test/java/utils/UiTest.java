package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Todo;
import duke.utils.CortanaException;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    @DisplayName("No task left message should be shown")
    public void showNoTaskLeftMessage() {
        String noTaskLeft = "You are done for the day, or are you?";
        assertEquals(noTaskLeft, ui.noTaskLeft());
    }


    @Test
    @DisplayName("Added task message should be shown")
    public void showAddedTaskMessage() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo dummyTodo = new Todo("read book");
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "task" : "tasks";
        String addedTask = "Got it. I've added this task: \n" + " " + dummyTodo
                + "\nNow you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
        assertEquals(addedTask, ui.addedTask(tasks, dummyTodo));
    }

    @Test
    @DisplayName("Deleted task message should be shown")
    public void showDeletedTaskMessage() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo dummyTodo = new Todo("read book");
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        String deletedTask = "Noted. I've removed this task: \n" + " " + dummyTodo + "\n"
                + "Now you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
        assertEquals(deletedTask, ui.deletedTask(tasks, dummyTodo));
    }

    @Test
    @DisplayName("Deleted all message should be shown")
    public void showDeletedAllMessage() {
        String deletedAll = "All tasks have been removed!";
        assertEquals(deletedAll, ui.deletedAll());
    }

    @Test
    @DisplayName("Exit message should be shown with")
    public void showExitedMessage() {
        String exited = "Bye. Hope to see you again soon!";
        assertEquals(exited, ui.exited());
    }

    @Test
    @DisplayName("Listed tasks message should be shown")
    public void showList() {
        Todo dummyTodo = new Todo("read book");
        String listed = 1 + "." + dummyTodo;
        assertEquals(listed, ui.listed(1, dummyTodo));
    }

    @Test
    @DisplayName("Marked task message should be shown")
    public void showMarkedMessage() {
        Todo dummyTodo = new Todo("read book");
        dummyTodo.setDone(true);
        String marked = "Nice! I've marked this task as done: \n " + dummyTodo;
        assertEquals(marked, ui.marked(dummyTodo));
    }

    @Test
    @DisplayName("Unmarked task message should be shown")
    public void showUnmarkedMessage() {
        Todo dummyTodo = new Todo("read book");
        dummyTodo.setDone(false);
        String unmarked = "OK, I've marked this task as not done yet: \n " + dummyTodo;
        assertEquals(unmarked, ui.unmarked(dummyTodo));
    }

    @Test
    @DisplayName("Tasks on same date message should be shown")
    public void showTasksOnSameDateMessage() {
        String tasksOnSameDate = String.format("Found 2 tasks with date/time %s.", "2022-01-24");
        assertEquals(tasksOnSameDate, ui.foundTaskOnSameDate(2, "2022-01-24").trim());
    }

    @Test
    @DisplayName("Tasks with search keyword should be shown")
    public void showFoundTaskMessage() {
        String tasksOnSameDate = "Found 1 task containing keyword \"book\".";
        assertEquals(tasksOnSameDate, ui.foundTasksMatchKeyword(1, "book").trim());
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidDateTime() {
        try {
            Parser.parse("show all 2022-13-13");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date/time!", ui.showErrorMessage(e.getMessage()));
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidDateTimeFormat() {
        try {
            Parser.parse("show all 2022/12/13");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date time format! Please follow the format yyyy-M-d HHmm!",
                    ui.showErrorMessage(e.getMessage()));
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingDateTime() {
        try {
            Parser.parse("show all");
            fail();
        } catch (Exception e) {
            assertEquals("Please specify the date time you are looking for!",
                    ui.showErrorMessage(e.getMessage()));
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidCommand() {
        try {
            Parser.parse("something invalid");
            fail();
        } catch (CortanaException e) {
            assertEquals("I don't know what that means :(", ui.showErrorMessage(e.getMessage()));
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingByKeyword() {
        try {
            Parser.parse("deadline book ticket by 2022-01-25");
            fail();
        } catch (CortanaException e) {
            assertEquals("Please specify the deadline time with the /by keyword!",
                    ui.showErrorMessage(e.getMessage()));
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingAtKeyword() {
        try {
            Parser.parse("event conference at 2022-01-25");
            fail();
        } catch (CortanaException e) {
            assertEquals("Please specify the event time with the /at keyword!",
                    ui.showErrorMessage(e.getMessage()));
        }
    }
}
