package jose;

import jose.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getSizeTest(){
        assertEquals(0, new TaskList().getSize());
    }

    @Test
    public void getTasksTest(){
        assertEquals(new ArrayList<>(), new TaskList().getTasks());
    }
}
