package duke;
import main.java.duke.Event;
import main.java.duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toFileFormatTest(){
        assertEquals("E,false,test description,2022-01-01 12:00",
                new Event("test description", "2022-01-01 12:00").toFileFormat());
    }
}
