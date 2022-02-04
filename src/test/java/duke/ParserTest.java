package duke;

import gui.Output;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }

    @Test
    public void parseIsByeTest_success(){
        assertEquals(Output.printWhatDoesThatMean(), Parser.parseIsBye("Bye", null));
    }

    @Test
    public void parseFileDataTest_success(){
        Deadline expectedDeadline = new Deadline("eat", 1, "tmr", true);
        Deadline actualDeadline = (Deadline) Parser.parseFileData("D---false---eat---tmr");
        assertEquals(expectedDeadline.toString().substring(3), actualDeadline.toString().substring(3));
    }

}
