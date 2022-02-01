import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bobby.Bobby;
import bobby.Parser;
import bobby.Storage;
import bobby.Task;
import bobby.TaskList;

class ParserTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testInputToDo() throws IOException {
        Parser.parse(new TaskList(new ArrayList<Task>(), new Storage("bobby.txt")), "todo 2103 iP",
                new Bobby("bobby.txt"));
        assertEquals("Bobby heard: [T][ ] 2103 iP" + System.lineSeparator()
                + "Bobby remembers 1 task(s).", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInputDeadLine() throws IOException {
        Parser.parse(new TaskList(new ArrayList<Task>(), new Storage("bobby.txt")),
                "deadline cs2106 quiz /by 2022-01-31", new Bobby("bobby.txt"));
        assertEquals("Bobby heard: [D][ ] cs2106 quiz (by: Jan 31 2022)" + System.lineSeparator()
                + "Bobby remembers 1 task(s).", outputStreamCaptor.toString().trim());
    }

    @Test
    public void parseInputs_invalidInputs_bobbyExceptionThrown() throws IOException {
        Parser.parse(new TaskList(new ArrayList<Task>(), new Storage("bobby.txt")), "random input",
                new Bobby("bobby.txt"));
        assertEquals("Bobby does not understand you. Please use valid inputs.",
                outputStreamCaptor.toString().trim());
    }
}