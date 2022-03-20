package duke.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;



public class UiTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUpForSystemOut() {
        System.setOut(new PrintStream(outContent));
    }
}
