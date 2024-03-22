import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dukeclasses.Deadline;
import dukeclasses.DukeException;
import dukeclasses.Event;
import dukeclasses.Task;
import dukeclasses.TaskList;
import dukeclasses.ToDo;

public class TaskListTest {

    @Test
    public void testEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTaskList().size());
    }

    @Test
    public void addTask_newTask_listOfSize1() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        ToDo todo = new ToDo("do homework");
        taskList.addTask(todo);
        assertEquals(todo , taskList.getTaskList().get(0));
        assertEquals(1, taskList.getTaskList().size());
    }

    @Test
    public void deleteTask_listOfSize3_listOfSize1() {
        TaskList taskList = new TaskList(new ArrayList<Task>());

        ToDo todo = new ToDo("do homework");
        Deadline deadline = new Deadline("swim", LocalDate.parse("2021-10-15"), null);
        Event event = new Event("run", LocalDate.parse("2021-10-10"), null);

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask((event));

        try {
            taskList.deleteTask(0);
            taskList.deleteTask(1);
        } catch (DukeException error) {
            fail();
        }

        assertEquals(1, taskList.getTaskList().size());
        assertEquals(deadline, taskList.getTaskList().get(0));
    }

    @Test
    public void updateTask_unmarkListOf3Task_listWith1MarkedAnd2UnmarkedTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());

        ToDo todo = new ToDo("do homework");
        Deadline deadline = new Deadline("swim", LocalDate.parse("2021-10-15"), null);
        Event event = new Event("run", LocalDate.parse("2021-10-10"), null);

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask((event));

        Task updatedTask = taskList.updateTask(2, true);
        Task updatedTask2 = taskList.updateTask(1, false);

        assertEquals(true, updatedTask.getIsDone());
        assertEquals(event, updatedTask);

        assertEquals(false, updatedTask2.getIsDone());
        assertEquals(deadline, updatedTask2);

        assertEquals(false, taskList.getTaskList().get(0).getIsDone());
        assertEquals(todo, taskList.getTaskList().get(0));
    }

}
