import dukeclasses.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    
    @Test
    public void testEventIdentify() {
        assertEquals("[T][ ] do homework",
            new ToDo("do homework").identify());
    }

    @Test
    public void testSetIsDone() {
        ToDo todo = new ToDo("do homework");
        todo.setDone(true);
        assertEquals("[T][X] do homework", todo.identify());
        todo.setDone(false);
        assertEquals("[T][ ] do homework", todo.identify());
    }

    @Test
    public void testGetDescription() {
        assertEquals(" ", new ToDo(" ").getDescription());
    }

    @Test
    public void testGetIsDone() {
        ToDo todo = new ToDo("do homework");
        assertEquals(false, todo.getIsDone());
        todo.setDone(true);
        assertEquals(true, todo.getIsDone());
        todo.setDone(false);
        assertEquals(false, todo.getIsDone());
    }

}
