package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void switchStatus() {
        Task task = new Task("Test task");
        assertEquals(false, task.getStatus());
        task.switchStatus();
        assertEquals(true, task.getStatus());
        task.switchStatus();
        assertEquals(false, task.getStatus());
    }

    @Test
    void markAsDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        assertEquals(true, task.getStatus());
        task.markAsDone();
        assertEquals(true, task.getStatus());
    }

    @Test
    void markAsNotDone() {
        Task task = new Task("Test task");
        task.markAsNotDone();
        assertEquals(false, task.getStatus());
        task.markAsNotDone();
        assertEquals(false, task.getStatus());
    }

    @Test
    void getStatusIcon() {
        Task task = new Task("Test task");
        assertEquals(" ", task.getStatusIcon());
        task.switchStatus();
        assertEquals("X", task.getStatusIcon());
        task.switchStatus();
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    void getName() {
        Task task = new Task ("Test task");
        assertEquals("Test task", task.getName());
        task = new Task ("Test task 1 2 3");
        assertEquals("Test task 1 2 3", task.getName());
    }
}