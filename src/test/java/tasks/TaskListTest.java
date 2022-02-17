package tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    /**
     * Test method for the TaskList class.
     */
    @Test
    public void testTaskList() {
        Task task1 = new Task("read book");
        Task task2 = new Task("go away");
        TaskList.add(task1);
        TaskList.add(task2);
        ArrayList<Task> tempList = new ArrayList<>();
        tempList.add(task1);
        tempList.add(task2);
        assertEquals(tempList, TaskList.dukeList);
    }
}
