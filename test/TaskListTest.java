import connor.task.TaskList;
import java.util.ArrayList;

import connor.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    @Test
    public void testTaskListMethods() {
        TaskList.setTasks(new ArrayList<>());
        assertEquals(new ArrayList<>(), TaskList.getTasks());
        assertEquals(0, TaskList.getNoOfTasks());

        TaskList.addTask(TaskType.TODO, "Things");
        assertEquals(1,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /by Monday");
        assertEquals(2,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at Monday");
        assertEquals(2,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at   ");
        assertEquals(2,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "     /at Monday");
        assertEquals(2,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing /at Monday");
        assertEquals(3,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing/atMonday");
        assertEquals(4,TaskList.getNoOfTasks());
        TaskList.addTask(TaskType.EVENT, "ThingMonday");
        assertEquals(4,TaskList.getNoOfTasks());

        TaskList.deleteTask(2);
        assertEquals(3,TaskList.getNoOfTasks());
        TaskList.deleteTask(999999);
        assertEquals(3,TaskList.getNoOfTasks());
        TaskList.deleteTask(-999999);
        assertEquals(3,TaskList.getNoOfTasks());

        TaskList.clearTasks();
        assertEquals(0,TaskList.getNoOfTasks());
    }
}
