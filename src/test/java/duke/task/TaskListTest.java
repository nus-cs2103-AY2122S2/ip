package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.io.UserInput;

public class TaskListTest {
    /*
    Java code normally use camelCase for method names e.g., testStringConversion
    but when writing test methods, sometimes another convention is used:
    whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    e.g., intDivision_zeroDivisor_exceptionThrown

    Tests use Assert.assertEquals(expected, actual) methods to compare the expected output with the actual output.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    //    @Test
    //    public void addTask_addTodoBuyBreadTask_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("T", "todo", "buy bread", false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[T][ ] buy bread\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }
    //
    //    @Test
    //    public void addTask_addTodoBuyCandyTask_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("T", "todo", "buy candy", false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[T][ ] buy candy\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }
    //
    //    @Test
    //    public void addTask_addEventHalloween_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("E", "event", "halloween ", "at Sun" , false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[E][ ] halloween (at: Sun)\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }
    //
    //    @Test
    //    public void addTask_addEventCny_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("E", "event", "cny ", "at Tue" , false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[E][ ] cny (at: Tue)\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }
    //
    //    @Test
    //    public void addTask_addDeadlineProject_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("D", "deadline", "proj ", "by 2022-01-31" , false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[D][ ] proj (by: 2022-01-31)\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }
    //
    //    @Test
    //    public void addTask_addDeadlineHomework_success() throws DukeException, IOException {
    //        setUpStreams();
    //        TaskList taskList = new TaskList();
    //        UserInput testInput = new UserInput("D", "deadline", "homework ", "by 2022-01-31" , false);
    //        taskList.addTask(testInput);
    //        assertEquals("________________________________________\n"
    //                        + "Got it. I've added this task:\n" + "[D][ ] homework (by: 2022-01-31)\n"
    //                        + "Now you have 1 tasks in the list\n"
    //                        + "________________________________________\n",
    //                outContent.toString());
    //        restoreStreams();
    //    }

    @Test
    public void addTask_addTodoBuyBreadTask_exceptionThrown() throws DukeException, IOException {
        setUpStreams();
        TaskList taskList = new TaskList();
        UserInput testInput = new UserInput("T", "todo", "", false);
        try {
            taskList.addTask(testInput);
            assertEquals("________________________________________\n"
                            + "Got it. I've added this task:\n" + "[T][ ] buy bread\n"
                            + "Now you have 1 tasks in the list\n"
                            + "________________________________________\n",
                    outContent.toString());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
        restoreStreams();
    }

    @Test
    public void addTask_addEventHalloween_exceptionThrownNoDescription() throws DukeException, IOException {
        setUpStreams();
        TaskList taskList = new TaskList();
        UserInput testInput = new UserInput("E", "event", "", "" , false);
        try {
            taskList.addTask(testInput);
            assertEquals("________________________________________\n"
                            + "Got it. I've added this task:\n" + "[E][ ] halloween (at: Sun)\n"
                            + "Now you have 1 tasks in the list\n"
                            + "________________________________________\n",
                    outContent.toString());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a event cannot be empty.", e.getMessage());
        }
        restoreStreams();
    }

    @Test
    public void addTask_addEventHalloween_exceptionThrownNoTime() throws DukeException, IOException {
        setUpStreams();
        TaskList taskList = new TaskList();
        UserInput testInput = new UserInput("E", "event", "halloween ", "" , false);
        try {
            taskList.addTask(testInput);
            assertEquals("________________________________________\n"
                            + "Got it. I've added this task:\n" + "[E][ ] halloween (at: Sun)\n"
                            + "Now you have 1 tasks in the list\n"
                            + "________________________________________\n",
                    outContent.toString());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Remember to set a time.", e.getMessage());
        }
        restoreStreams();
    }

    @Test
    public void addTask_addDeadlineProject_exceptionThrownNoDescription() throws DukeException, IOException {
        setUpStreams();
        TaskList taskList = new TaskList();
        UserInput testInput = new UserInput("D", "deadline", "", "" , false);
        try {
            taskList.addTask(testInput);
            assertEquals("________________________________________\n"
                            + "Got it. I've added this task:\n" + "D][ ] proj (by: 2022-01-31)\n"
                            + "Now you have 1 tasks in the list\n"
                            + "________________________________________\n",
                    outContent.toString());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
        restoreStreams();
    }

    @Test
    public void addTask_addDeadlineProject_exceptionThrownNoTime() throws DukeException, IOException {
        setUpStreams();
        TaskList taskList = new TaskList();
        UserInput testInput = new UserInput("D", "deadline", "proj ", "" , false);
        try {
            taskList.addTask(testInput);
            assertEquals("________________________________________\n"
                            + "Got it. I've added this task:\n" + "D][ ] proj (by: 2022-01-31)\n"
                            + "Now you have 1 tasks in the list\n"
                            + "________________________________________\n",
                    outContent.toString());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Remember to set a time.", e.getMessage());
        }
        restoreStreams();
    }
}
