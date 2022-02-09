package connor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import connor.task.TaskList;
import connor.task.TaskType;

class TaskListTest {
    @Test
    public void testTaskListMethods() {
        TaskList.setTasks(new ArrayList<>());
        assertEquals(new ArrayList<>(), TaskList.getTasks());
        assertEquals(0, TaskList.getNumberOfTasks());

        TaskList.addTask(TaskType.TODO, "Things");
        assertEquals(1, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /by 19-02-2022 14:30");
        assertEquals(2, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at 19-02-2022 14:30");
        assertEquals(2, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at   ");
        assertEquals(2, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "     /at Monday");
        assertEquals(2, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing /at 19-02-2022 14:30");
        assertEquals(3, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing/at19-02-2022 14:30");
        assertEquals(4, TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "ThingMonday");
        assertEquals(4, TaskList.getNumberOfTasks());

        TaskList.deleteTask(2);
        assertEquals(3, TaskList.getNumberOfTasks());
        TaskList.deleteTask(999999);
        assertEquals(3, TaskList.getNumberOfTasks());
        TaskList.deleteTask(-999999);
        assertEquals(3, TaskList.getNumberOfTasks());

        TaskList.clearTasks();
        assertEquals(0, TaskList.getNumberOfTasks());
    }
}
