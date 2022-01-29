import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Ui;
import org.junit.jupiter.api.TestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UiTest {
    private final PrintStream STANDARD_OUT = System.out;
    private final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();

    Ui testUi = new Ui();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(STANDARD_OUT);
    }

    @Test
    public void welcomeTest() {
        String logo = " ____              _"
                + "\n|  _ \\ _   _ _____| | ____ __\n"
                + "| | | | | | |  ___| |/ /\\ v /\n"
                + "| |_| | |_| | |___|   <  | |\n"
                + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        String ans = logo + "Hello! I'm Ducky! :)\n" + "I am a duke.task manager.\n" +
                "Type 'help' for more information on the commands you can give me.\n" +
                "What can I do for you today?\n" +
                "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧\n";

        testUi.welcome();
        assertEquals(ans, OUTPUT_STREAM_CAPTOR.toString());
    }

    @Test
    public void showLineTest() {
        testUi.showLine();
        assertEquals("✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧\n", OUTPUT_STREAM_CAPTOR.toString());
    }

    @Test
    public void showErrorTest() {
        String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                "Let's try again ~(^.^)~\n" +
                "Type 'help' if you need to know how to use this command";
        testUi.showError(DESC_RESPONSE);
        assertEquals(DESC_RESPONSE + "\n", OUTPUT_STREAM_CAPTOR.toString());
    }

    @Test
    public void printHelpTest() {
        testUi.printHelp();

        String helpResponse = "> Type 'list' to see what you have in your duke.task list\n" +
                "> Type 'todo <message>' to put a todo in your list\n" +
                "> Type 'deadline <message> /by <deadline>' to put a deadline in your list." +
                "\n\t - duke.task.Deadline must be in 'DD/MM/YYYY' format" +
                "\n> Type 'event <message> /at <date>' to put an event in your list" +
                "\n\t - Date must be in 'DD/MM/YYYY' format" +
                "\n> Type 'mark <x>' to mark a duke.task in your list" +
                "\n> Type 'unmark <x>' to unmark a duke.task in your list\n";
        ;
        
        assertEquals(helpResponse, OUTPUT_STREAM_CAPTOR.toString());
    }
}
