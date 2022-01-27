package core.tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskListTest() {
        Task t1 = ToDo.getInstance("read book");
        Task t2 = Event.getInstance("friday hacks", "2022-02-23");
        ArrayList<Task> tempList = new ArrayList<>();
        tempList.add(t1);
        tempList.add(t2);

        TaskList taskList = TaskList.getInstance(tempList);
        assertEquals(tempList, taskList.getAllTasks());
    }
}
