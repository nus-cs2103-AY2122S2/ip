package walle;

import gui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void parseIsByeTest_success() {
        assertEquals(Ui.printWhatDoesThatMean(), Parser.parseIsBye("Bye", new TaskList()));
    }

    @Test
    public void parseFileDataTest_success() {
        Deadline expectedDeadline = new Deadline("eat", "tmr",1, true);
        Deadline actualDeadline = (Deadline) Parser.parseFileData("D---false---eat---tmr", new TaskList());
        assertEquals(expectedDeadline.toString().substring(3), actualDeadline.toString().substring(3));
    }

}
