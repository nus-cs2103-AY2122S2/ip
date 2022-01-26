package task.managers;

import duke.DukeException;
import duke.managers.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void TaskList_getSize_success() throws DukeException {
        TaskList taskList  = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        assertEquals(taskList.getSize(), 3);
    }

    @Test
    void TaskList_deleteTask_success() throws DukeException {
        TaskList taskList  = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.deleteTask(2);
        assertEquals(taskList.getSize(), 2);
    }

    @Test
    void TaskList_markTaskDone_success() throws DukeException {
        TaskList taskList  = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.markTaskDone(2, false);
        assertEquals(taskList.getTask(2).checkIsDone(), true);
    }

    @Test
    void TaskList_markTaskNotDone_success() throws DukeException {
        TaskList taskList  = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.markTaskDone(2, false);
        taskList.markTaskNotDone(2, false);
        assertEquals(taskList.getTask(2).checkIsDone(), false);
    }

}
