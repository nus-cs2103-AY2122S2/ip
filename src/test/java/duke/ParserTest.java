package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests methods from the Parser class.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class ParserTest {

    /**
     *  Tests the constructor of the parser class.
     */
    @Test
    public void testInputParser(){
        // First Testcase
        Parser tc1 = new Parser("deadline testing deadline z /by 2022-02-03");
        assertEquals(tc1.getCmd(), "deadline");
        assertEquals(tc1.getDesc(), "testing deadline z");
        assertEquals(tc1.getDate(), "2022-02-03");

        // Second Testcase
        Parser tc2 = new Parser("event testing event /at hello");
        assertEquals(tc2.getCmd(), "event");
        assertEquals(tc2.getDesc(), "testing event");
        assertEquals(tc2.getDate(), "hello");

        // Third Testcase
        Parser tc3 = new Parser("todo testing todo /by");
        assertEquals(tc3.getCmd(), "todo");
        assertEquals(tc3.getDesc(), "testing todo");
        assertEquals(tc3.getDate(), null);

        // Fourth Testcase
        Parser tc4 = new Parser("hello random testing /by today");
        assertEquals(tc4.getCmd(), "hello");
        assertEquals(tc4.getDesc(), "random testing");
        assertEquals(tc4.getDate(), "today");

    }
}