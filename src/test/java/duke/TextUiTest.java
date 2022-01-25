package duke;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextUiTest {
    private static final String DIVIDER = "===================================================";
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    @Test
    public void testDivider() {
        System.setOut(new PrintStream(byteArrayOutputStream));
        TextUi ui = new TextUi();
        ui.showDivider();
        assertEquals(DIVIDER, byteArrayOutputStream.toString().trim());
    }
}