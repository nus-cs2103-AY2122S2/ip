package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void size_two_success() {
        TaskList test = new TaskList();
        test.add(new ToDo("smn"));
        test.add(new ToDo("smn1"));
        assertEquals(2,test.size());
    }

    @Test
    public void isEmpty_success() {
        TaskList test = new TaskList();
        test.add(new ToDo("smn"));
        test.remove(0);
        assertEquals(true, test.isEmpty());
    }
}