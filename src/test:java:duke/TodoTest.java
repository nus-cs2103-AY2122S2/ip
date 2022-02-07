package duke;

import org.junit.test;
import static org.junit.assertEquals;
import duke.duke.Todo;

class TodoTest {
    
    @Test
    public void testTodo() {
        String test1 = "[T][ ] buy bread";
        String test2 = "[T][ ] help Kris do cs2103";
        assertEquals(test1, new Todo("buy bread").toString());
        assertEquals(test2, new Todo("help Kris do cs2103").toString());
    }




}