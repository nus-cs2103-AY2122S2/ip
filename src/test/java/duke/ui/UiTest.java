package duke.ui;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    Task task = new Task("test");
    int numberOfTask = 1;

    @Test
    void testGoodbyeMessage() {
        assertEquals("Bye. Hope to see you again soon!\r\n", ui.goodbye());
    }

    @Test
    void testTaskAddedMessage() {
        assertEquals("Got it. I've added this task:\n" + "[ ] test"
                + "\nNow you have " + numberOfTask + " tasks in the list.\r\n", ui.taskAddedMessage(task, numberOfTask));
    }

    @Test
    void greet() {
        assertEquals("Hello! I'm Duke\nWhat can I do for you?\n", ui.greet());
    }

    @Test
    void showLine() {
        assertEquals("-------------------------------\n\r\n", ui.showLine());
    }

    @Test
    void taskMarkedMessage() {
        assertEquals("Nice! I've marked this task as done:\n" + task.toString() + "\r\n",
                ui.taskMarkedMessage(task));
    }

    @Test
    void taskUnmarkedMessage() {
        assertEquals("OK! I've marked this task as not done yet:\n" + task.toString() + "\r\n",
                ui.taskUnmarkedMessage(task));
    }

    @Test
    void taskDeleteMessage() {
        assertEquals("OK! I've deleted this task:\n" + task.toString() + "\nNow you have "
                + numberOfTask + " tasks in the list.\r\n", ui.taskDeleteMessage(task, numberOfTask));
    }
}