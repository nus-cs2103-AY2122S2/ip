import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dukeclasses.ToDo;

public class ToDoTest {
    @Test
    public void testEventIdentify() {
        assertEquals("[T][ ] do homework\n",
            new ToDo("do homework").toString());
    }

    @Test
    public void testSetIsDone() {
        ToDo todo = new ToDo("do homework");
        todo.setDone(true);
        assertEquals("[T][X] do homework\n", todo.toString());
        todo.setDone(false);
        assertEquals("[T][ ] do homework\n", todo.toString());
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
