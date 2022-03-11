package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        Task t1 = new ToDo("task1", false);
        Task t2 = new Deadline("task2", "2009-12-09", false);
        ArrayList<Task> arrList = new ArrayList<>();
        TaskList tm = new TaskList(arrList);
        tm.addTask(t1);
        tm.addTask(t2);
        ArrayList<Task> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        assertEquals(true, tm.getTaskList().equals(t));
    }

}