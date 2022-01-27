package yale.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getTask() {
        TaskList list = new TaskList();
        Task task = new Task("work", false);
        list.addToList(task);
        assertEquals(task, list.getTask(0));
    }

    @Test
    void deleteTask() {
        TaskList list = new TaskList();
        Task task = new Task("work", false);
        list.addToList(task);
        list.deleteTask(0);
        assertEquals(0, (list.getSize()));
    }


    @Test
    void markFeature() {
        TaskList list = new TaskList();
        Task task = new Task("work", false);
        list.addToList(task);
        list.markFeature("mark 1", list);
        assertEquals(task.isMarked, list.getTask(0).isMarked);
    }

}