import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.Ui;

public class UiTest {

    private final PrintStream STANDARD_OUTPUT = System.out;
    private final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
    }

    @Test
    public void testGoodbye() {
        Ui ui = new Ui();
        ui.goodbye();
        String expected = "------------------------------------------------------\n"
                + "Bye. Have a great day!\n======================================================";
        assertEquals(expected, OUTPUT_STREAM_CAPTOR.toString().trim());
    }

    @Test
    public void testGreet() {
        Ui ui = new Ui();
        ui.greet();
        String expected = "Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n"
                + "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the "
                + "task number!\n======================================================";
        assertEquals(expected, OUTPUT_STREAM_CAPTOR.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(STANDARD_OUTPUT);
    }
}
