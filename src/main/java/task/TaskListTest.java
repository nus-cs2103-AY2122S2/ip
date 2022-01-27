package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void addTask() {
        TaskList tl = new TaskList();
        assertEquals("Ah sure. I've added this task:\n" +
                "      [T][ ] sleep\n" +
                "    Now you have 1 task in the list.", tl.addTask(new Todo("sleep"), 1));
    }

    @Test

    void removeTask() {
        TaskList tl = new TaskList();
        assertEquals("Less work for you then less work for me then. I've removed this task:\n" +
                "      [T][ ] sleep\n" +
                "    Now you have 0 tasks in the list.", tl.removeTask(new Todo("sleep"),0));
    }


}
