package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testGreet() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui.getInstance().greet();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("\t------------------------------------\r\n"
                + "\tHi! I'm Megumin\r\n"
                + "\tWhat do you need?\r\n"
                + "\t------------------------------------\r\n\r\n", lines);
    }
}
