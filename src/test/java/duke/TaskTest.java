package duke;
import main.java.duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIconTest(){
        assertEquals(" ", new Task("test description").getStatusIcon());
    }

    @Test
    public void toStringTest(){
        assertEquals("[ ] test description", new Task("test description").toString());
    }
}
