package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TaskListTest {

    @Test

    void addTask() {
        TaskList tl = new TaskList();
        assertEquals("Ah sure. I've added this task:\n"
                + "      [T][ ] sleep\n"
                + "    Now you have 1 task in the list.", tl.execute("todo", "sleep"));
    }



    @Test

    void removeTask() {
        TaskList tl = new TaskList();
        assertEquals("Less work for you then less work for me then. I've removed this task:\n"
                + "      [T][ ] sleep\n"
                + "    Now you have 0 tasks in the list.", tl.execute("delete", "1"));
    }




}
