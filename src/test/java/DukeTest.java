import duke.duke.Event;
import duke.duke.Task;
import duke.duke.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void lineTest() {
        // Set Up
        Ui ui = new Ui();
        String expected = "------------------------------------";
        // Create Output Stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        // Call Method
        ui.showWelcome();
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        // Compare
        assertEquals(expected,actual);
    }

    @Test
    public void taskNameTest() {
        // Set Up
        Task task = new Task("Hello");
        String expected = "Hello";
        // Compare
        assertEquals(expected,task.getName());
    }

    @Test
    public void eventStringTest() {
        // Set Up
        Event event = new Event("Hello", "1111-11-11");
        String expected = "[E][ ] Hello (at: Nov 11 1111)";
        // Compare
        assertEquals(expected,event.toString());
    }
}