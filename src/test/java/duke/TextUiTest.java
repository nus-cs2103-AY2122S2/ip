package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


public class TextUiTest {
    private static final String DIVIDER = "===================================================";
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    @Test
    public void testDivider() {
        TextUi ui = new TextUi();
        assertEquals(DIVIDER, ui.showDivider());
    }
}
