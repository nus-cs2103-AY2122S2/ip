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
    void testGoodbyeMessage(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.goodbye();
        assertEquals("Bye. Hope to see you again soon!\r\n",outContent.toString());
    }

    @Test
    void testTaskAddedMessage(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.taskAddedMessage(task, numberOfTask);

        assertEquals("Got it. I've added this task:\n" + "[ ] test"
                + "\nNow you have " + numberOfTask + " tasks in the list.\r\n", outContent.toString());
    }

    @Test
    void greet() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.greet();
        assertEquals("-------------------------------\n"
                + "Hello! I'm Duke\nWhat can I do for you?\n"
                + "-------------------------------\n\r\n", outContent.toString());
    }

    @Test
    void showLine() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.showLine();
        assertEquals("-------------------------------\n\r\n",outContent.toString());
    }

    @Test
    void taskMarkedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.taskMarkedMessage(task);
        assertEquals("Nice! I've marked this task as done:\n" + task.toString() + "\r\n",
                outContent.toString());
    }

    @Test
    void taskUnmarkedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.taskUnmarkedMessage(task);
        assertEquals("OK! I've marked this task as not done yet:\n" + task.toString() + "\r\n",
                outContent.toString());
    }

    @Test
    void taskDeleteMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.taskDeleteMessage(task,numberOfTask);
        assertEquals("OK! I've deleted this task:\n" + task.toString() + "\nNow you have "
                + numberOfTask + " tasks in the list.\r\n", outContent.toString());
    }
}