package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Todo;
import duke.utils.CortanaException;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;

    @BeforeEach
    public void setUpUi() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Welcome message should be shown")
    public void showWelcomeMessage() {
        ui.showWelcome();
        String logo = "\n"
                + "   ____                  _                           \n"
                + "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n"
                + " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n"
                + " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n"
                + "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n"
                + "                                                     \n";
        assertEquals(String.format("Hello from\n%s\nMy name is Cortana, what can I do for you?", logo),
                outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Separator line Ui should shown")
    public void showSeparatorLine() {
        ui.showLine();
        String line = "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-";
        assertEquals(line, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Input command should be read")
    public void readCommand() {
        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("todo read book\n".getBytes());
        System.setIn(inputStreamCaptor);
        String commandRead = ui.readCommand();
        assertEquals("todo read book", commandRead);
    }

    @Test
    @DisplayName("No task left message should be shown")
    public void showNoTaskLeftMessage() {
        ui.noTaskLeft();
        String noTaskLeft = "You are done for the day, or are you?";
        assertEquals(noTaskLeft, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Task message should be shown")
    public void printTask() {
        Todo dummyTodo = new Todo("read book");
        Deadline dummyDeadline = new Deadline("return book",
                LocalDateTime.of(LocalDate.of(2022, 1, 25), LocalTime.MAX));
        ui.printTask(dummyTodo);
        String dummyTodoString = "[T][ ] read book";
        assertEquals(dummyTodoString, outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        ui.printTask(dummyDeadline);
        String dummyDeadlineString = "[D][ ] return book(by: Tuesday, January 25, 2022)";
        assertEquals(dummyDeadlineString, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Added task message should be shown")
    public void showAddedTaskMessage() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo dummyTodo = new Todo("read book");
        ui.addedTask(tasks, dummyTodo);
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        String addedTask = "Got it. I've added this task: \n" + " " + dummyTodo
                + "\nNow you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
        assertEquals(addedTask, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Deleted task message should be shown")
    public void showDeletedTaskMessage() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo dummyTodo = new Todo("read book");
        ui.deletedTask(tasks, dummyTodo);
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        String deletedTask = "Noted. I've removed this task: \n" + " " + dummyTodo + "\n"
                + "Now you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
        assertEquals(deletedTask, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Deleted all message should be shown")
    public void showDeletedAllMessage() {
        ui.deletedAll();
        String deletedAll = "All tasks have been removed!";
        assertEquals(deletedAll, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Exit message should be shown with")
    public void showExitedMessage() {
        ui.exited();
        String exited = "Bye. Hope to see you again soon!";
        assertEquals(exited, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Listed tasks message should be shown")
    public void showList() {
        Todo dummyTodo = new Todo("read book");
        ui.listed(1, dummyTodo);
        String listed = 1 + "." + dummyTodo;
        assertEquals(listed, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Marked task message should be shown")
    public void showMarkedMessage() {
        Todo dummyTodo = new Todo("read book");
        dummyTodo.setDone(true);
        ui.marked(dummyTodo);
        String marked = "Nice! I've marked this task as done: \n " + dummyTodo;
        assertEquals(marked, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Unmarked task message should be shown")
    public void showUnmarkedMessage() {
        Todo dummyTodo = new Todo("read book");
        dummyTodo.setDone(false);
        ui.unmarked(dummyTodo);
        String unmarked = "OK, I've marked this task as not done yet: \n " + dummyTodo;
        assertEquals(unmarked, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Tasks on same date message should be shown")
    public void showTasksOnSameDateMessage() {
        ui.foundTaskOnSameDate(2, "2022-01-24");
        String tasksOnSameDate = String.format("Found 2 tasks with date/time %s.", "2022-01-24");
        assertEquals(tasksOnSameDate, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Tasks with search keyword should be shown")
    public void showFoundTaskMessage() {
        ui.foundTasksMatchKeyword(1, "book");
        String tasksOnSameDate = "Found 1 task containing keyword \"book\".";
        assertEquals(tasksOnSameDate, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidDateTime() {
        try {
            Parser.parse("show all 2022-13-13");
            fail();
        } catch (Exception e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("Invalid date/time!", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidDateTimeFormat() {
        try {
            Parser.parse("show all 2022/12/13");
            fail();
        } catch (Exception e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("Invalid date time format! Please follow the format yyyy-M-d HHmm!",
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingDateTime() {
        try {
            Parser.parse("show all");
            fail();
        } catch (Exception e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("Please specify the date time you are looking for!",
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_invalidCommand() {
        try {
            Parser.parse("something invalid");
            fail();
        } catch (CortanaException e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("I don't know what that means :(", outputStreamCaptor.toString().trim());
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingByKeyword() {
        try {
            Parser.parse("deadline book ticket by 2022-01-25");
            fail();
        } catch (CortanaException e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("Please specify the deadline time with the /by keyword!",
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    @DisplayName("Error message should be shown")
    public void showErrorMessage_missingAtKeyword() {
        try {
            Parser.parse("event conference at 2022-01-25");
            fail();
        } catch (CortanaException e) {
            ui.showErrorMessage(e.getMessage());
            assertEquals("Please specify the event time with the /at keyword!",
                    outputStreamCaptor.toString().trim());
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
