package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTaskTest {
    @Test
    public void todoTask_valid_success(){
        assertTrue(new TodoTask("Title 1") instanceof TodoTask);
        assertTrue(new TodoTask("Title 2", true) instanceof TodoTask);
    }

    @Test
    public void todoTask_toOutputLine_success(){
        assertEquals(new TodoTask("Title 1").toOutputLine(),
                "T | 0 | Title 1");
        assertEquals(new TodoTask("Title 2", true).toOutputLine(),
                "T | 1 | Title 2");

    }

    @Test
    public void todoTask_toString_success(){
        assertEquals(new TodoTask("Title 1").toString(),
                "Title 1");
        assertEquals(new TodoTask("Title 2", true).toString(),
                "Title 2");
    }
}
