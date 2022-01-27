package duke.ui;

import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUpForSystemOut() {
        System.setOut(new PrintStream(outContent));
    }
}
