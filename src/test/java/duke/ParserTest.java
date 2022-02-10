package duke;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void ParserTest() {
        String testSpace = "hello world";
        String[] output1 = Parser.splitSpace(testSpace);

        String testForwardSlash = "hello/world";
        String[] output2 = Parser.splitForwardSlash(testForwardSlash);

        assertEquals("world", output1[1]);
        assertEquals("hello", output2[0]);
    }
}
