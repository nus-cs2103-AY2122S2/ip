package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markTest() {
        ArrayList<Task> array = new ArrayList();
        array.add(new Todo("Wash clothes"));
        array.add(new Todo("Dry clothes"));
        array.add(new Todo("Wash dishes"));


        assertEquals("Nice! I've marked this task as done:\n" + "[X] Wash clothes", array.mark("mark 1"));
    }

    @Test
    public void unmarkTest() {
        ArrayList<Task> array = new ArrayList();
        array.add(new Todo("Wash clothes"));
        array.add(new Todo("Dry clothes"));
        array.add(new Todo("Wash dishes"));


        assertEquals("Ok! I've marked this task as not done yet:\n" + "[ ] Wash clothes", array.mark("unmark 1"));
    }    
}