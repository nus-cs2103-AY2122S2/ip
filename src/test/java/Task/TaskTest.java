package task;

import jose.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIconTest_marked(){
        assertEquals("[X]", new Task("", true).getStatusIcon());
    }

    @Test
    public void getStatusIconTest_unmarked(){
        assertEquals("[ ]", new Task("", false).getStatusIcon());
    }

    @Test
    public void formatDataTest(){
        assertEquals("0|test", new Task("test").formatData());
    }

    @Test
    public void toStringTest(){
        assertEquals("[ ] test", new Task("test").toString());
    }
}