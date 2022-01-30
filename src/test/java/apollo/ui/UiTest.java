package apollo.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String border = "\t________________________________________________________________________\n";

    @Test
    void getUserCommand_trailingLeadingSpaces() {
        String input = "   test 1 2  3   \n";
        Ui ui = new Ui(new ByteArrayInputStream(input.getBytes()), new PrintStream(outputStream));
        assertEquals("test 1 2  3", ui.getUserCommand());
    }

    @Test
    void printMessage_singleLine() {
        Ui ui = new Ui(new ByteArrayInputStream("".getBytes()), new PrintStream(outputStream));
        ui.printMessage("test Line");
        String expected = border
                + "\t test Line\n"
                + border;
        assertEquals(expected.replace("\n", System.lineSeparator()), outputStream.toString());
    }

    @Test
    void printMessage_multiLine() {
        Ui ui = new Ui(new ByteArrayInputStream("".getBytes()), new PrintStream(outputStream));
        ui.printMessage("test Line 1. \nTestline 2.\ntest line 3");
        String expected = border
                + "\t test Line 1. \n\t Testline 2.\n\t test line 3\n"
                + border;
        assertEquals(expected.replace("\n", System.lineSeparator()), outputStream.toString());
    }
}
