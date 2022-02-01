package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.duke.Event;



public class EventTest {
    @Test
    public void toFileFormatTest() {
        assertEquals("E,false,test description,2022-01-01 12:00",
                new Event("test description", "2022-01-01 12:00").toFileFormat());
    }
}
