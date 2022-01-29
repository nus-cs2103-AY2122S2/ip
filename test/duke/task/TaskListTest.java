package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void checkSize_add1Task_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add( new Todo("todo task description", false) );
        assertEquals(1, taskList.size());
    }

    @Test
    void checkEmpty_add0Task_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertEquals(true, taskList.isEmpty());
    }

    @Test
    void getTask_add1Task_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add(new Todo("todo task description", false));
        Task retrievedTask = taskList.get(0);
        assertEquals(retrievedTask.toString(), "[T][ ] todo task description");
    }

    @Test
    void removeTask_add1Task_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add(new Todo("todo task description", false));
        Task removedTask = taskList.remove(0);
        assertEquals(removedTask.toString(), "[T][ ] todo task description");
    }

    @Test
    void addTask_add1Task_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertEquals(true, taskList.add(new Todo("todo task description", false)));
    }

    @Test
    void getTasks_addFewTasks_success() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new Todo("todo description", false));
            tasks.add(new Deadline("deadline description", "2021-01-01", false));
            tasks.add(new Event("event description", "2021-01-01", false));

            TaskList taskList = new TaskList(tasks);

            assertEquals(tasks, taskList.getTasks());
        } catch (DukeException e) {
            fail();
        }
    }
}