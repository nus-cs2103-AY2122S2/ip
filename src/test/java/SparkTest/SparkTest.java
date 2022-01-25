package SparkTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Spark;
import spark.Ui;
import spark.commands.commandtypes.AddToDoCommand;
import spark.exceptions.SparkException;
import spark.tasks.TaskList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SparkTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final String normalLineSeparator = "----------------------------------------------------------------------";
    private static final String exceptionLineSeparator = "-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!";

    @BeforeEach
    public void recordOutput() {
        // redirect any messages sent to System.out to
        // an object for analysis in the testcase
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testWelcomeMessage() {
        // a unit test for Ui#printWelcomeMessage() method
        String expectedWelcomeMessage = String.format("%s\n%s\n%s\n%s\n",
                normalLineSeparator,
                "Hello I'm Spark!",
                "What can I do for you?",
                normalLineSeparator
        );

        Ui ui = new Ui();
        ui.printWelcomeMessage();

        assertEquals(expectedWelcomeMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testAddValidToDo() {
        // a unit test for TaskList#addToDo() method
        TaskList taskList = new TaskList();
        String[] tokens = new String[]{"todo", "buy", "milk"};

        try {
            taskList.addToDo(tokens);
        } catch (SparkException e) {
            fail("A new ToDo could not be added.");
        }

        String expectedMessage = String.format(
                "Got it, I've added this todo:\n" +
                "   [T][] buy milk\n" +
                "Now you have 1 tasks in the list.\n"
        );

        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        // redirect output to default System.out
        System.setOut(standardOut);
    }
}
