package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TaskListTest {
    @Test
    public void size_two_success() {
        TaskList test = new TaskList();
        test.add(new ToDo("smn"));
        test.add(new ToDo("smn1"));
        assertEquals(2, test.size());
    }

    @Test
    public void isEmpty_success() {
        TaskList test = new TaskList();
        test.add(new ToDo("smn"));
        test.remove(0);
        assertEquals(true, test.isEmpty());
    }
}
