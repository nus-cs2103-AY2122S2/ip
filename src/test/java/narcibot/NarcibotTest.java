package narcibot;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NarcibotTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void ParserTest() {
        String[] expected = new String[] {"deadline", "sleep", "11pm"};
        String[] output = new Parser().parse("deadline sleep /by 11pm");
        assertArrayEquals(expected, output);
    }

    @Test
    public void ToDoTest() throws IncorrectFormatException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ToDo todo = new ToDo("eat");
        assertEquals("[T][ ] eat", out.toString().replace ("\r","").replace("\n",""));
    }

    @Test
    public void EventTest() throws IncorrectFormatException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Event event = new Event("work","11am");
        assertEquals("[E][ ] work (at: 11am)", out.toString().replace("\r","").replace("\n",""));
    }
}
