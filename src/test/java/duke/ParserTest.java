package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }

    @Test
    public void parseIsByeTest(){
        assertEquals(false, Parser.parseIsBye("Bye"));
    }

    @Test
    public void parseFileDataTest(){
        Deadline expectedDeadline = new Deadline("eat", 1, "tmr", true);
        Deadline actualDeadline = (Deadline) Parser.parseFileData("D---false---eat---tmr");
        assertEquals(expectedDeadline.toString(), actualDeadline.toString());
    }

}
