package siri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void separatorTest() {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        String expectedOutput = "====================\n";
        new Ui().separator();
        assertEquals(expectedOutput, outputContent.toString());
    }

}
