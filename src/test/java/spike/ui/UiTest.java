package spike.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    // For testing system output
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(standardOut);
    }

    @Test
    public void getCommand_emptyCommand_getNextLine() {
        // For simulating system input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(" \nNext line\n".getBytes());
        PrintStream printStream = new PrintStream(outputStreamCaptor);

        Ui ui = new Ui(inputStream, printStream);
        assertEquals("Next line", ui.getCommand());
    }

    @Test
    public void printMsg_anyString_formattedResponse() {
        PrintStream printStream = new PrintStream(outputStreamCaptor);

        Ui ui = new Ui(System.in, printStream);
        ui.printMsg("Any string");
        String expected = "-------------------------------------------------\n"
                + "Any string"
                + "\n"
                + "-------------------------------------------------";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void getCommand_nonEmptyCommand_getCurrentLine() {
        // For simulating system input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Current line\n".getBytes());
        PrintStream printStream = new PrintStream(outputStreamCaptor);

        Ui ui = new Ui(inputStream, printStream);
        assertEquals("Current line", ui.getCommand());
    }

    @Test
    public void showLoadingError_noInput_errorMsg() {
        PrintStream printStream = new PrintStream(outputStreamCaptor);

        Ui ui = new Ui(System.in, printStream);
        ui.showLoadingError();
        String expected = "-------------------------------------------------\n"
                + "Sorry, I couldn't create the task list file for you."
                + "\n"
                + "-------------------------------------------------";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void greet_zeroListSize_greetNewUser() {
        Ui ui = new Ui();
        ui.greet(0);
        String expected = "-------------------------------------------------\n"
                + "Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?"
                + "\n"
                + "-------------------------------------------------";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void greet_nonZeroListSize_greetOldUser() {
        Random random = new Random();
        Ui ui = new Ui();
        ui.greet(random.nextInt(1000) + 1);
        String expected = "-------------------------------------------------\n"
                + "Welcome back! Enter 'list' command to see your task list."
                + "\n"
                + "-------------------------------------------------";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }


}
