package alfred.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Encapsulates tests for Alfred CLI user interface.
 */
public class AlfredUserInterfaceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void sandwichAndPrint_test() {

        // generate expected
        String line = "";
        for (int i = 0; i < 100; i++) {
            line += "-";
        }
        String expected = line + "\n" + "expected" + "\n" + line + "\n" + "\n";

        // set
        System.setOut(new PrintStream(this.outContent));

        // test
        AlfredUserInterface ui = new AlfredUserInterface();
        ui.sandwichAndPrint("expected");
        assertEquals(expected.trim().replace("\r", ""), outContent.toString().trim().replace("\r", ""));
    }

}


