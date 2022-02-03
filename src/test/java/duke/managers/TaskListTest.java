package duke.managers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Todo;

public class TaskListTest {
    @Test
    void taskList_getSize_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        assertEquals(taskList.getSize(), 3);
    }

    @Test
    void taskList_deleteTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.deleteTask(2);
        assertEquals(taskList.getSize(), 2);
    }

    @Test
    void taskList_markTaskDone_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.markTaskDone(2, false);
        assertEquals(taskList.getTask(2).checkIsDone(), true);
    }

    @Test
    void taskList_markTaskNotDone_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Go for a jog"), false);
        taskList.addTask(new Todo("Go for a run"), false);
        taskList.addTask(new Todo("Go for a hike"), false);
        taskList.markTaskDone(2, false);
        taskList.markTaskNotDone(2, false);
        assertEquals(taskList.getTask(2).checkIsDone(), false);
    }

}
