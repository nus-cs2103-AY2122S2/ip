package connor;

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
        assertEquals(0, TaskList.getNumberOfTasks());

        TaskList.addTask(TaskType.TODO, "Things");
        assertEquals(1,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /by Monday");
        assertEquals(2,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at Monday");
        assertEquals(2,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "Things /at   ");
        assertEquals(2,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.DEADLINE, "     /at Monday");
        assertEquals(2,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing /at Monday");
        assertEquals(3,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "Thing/atMonday");
        assertEquals(4,TaskList.getNumberOfTasks());
        TaskList.addTask(TaskType.EVENT, "ThingMonday");
        assertEquals(4,TaskList.getNumberOfTasks());

        TaskList.deleteTask(2);
        assertEquals(3,TaskList.getNumberOfTasks());
        TaskList.deleteTask(999999);
        assertEquals(3,TaskList.getNumberOfTasks());
        TaskList.deleteTask(-999999);
        assertEquals(3,TaskList.getNumberOfTasks());

        TaskList.clearTasks();
        assertEquals(0,TaskList.getNumberOfTasks());
    }
}
