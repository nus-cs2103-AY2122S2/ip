package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void printTaskCount_emptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        assertEquals("Now you have 0 task(s) in the list.", t.printTaskCount());
    }

    @Test
    public void PrintTaskCount_nonEmptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new Task("Test"));
        assertEquals("Now you have 1 task(s) in the list.", t.printTaskCount());
    }

    @Test
    public void printTaskList_emptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        assertEquals("You got no task now! Start by adding new tasks.", t.taskListToString());
    }

    @Test
    public void printTaskList_nonEmptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new ToDo("Read book"));
        assertEquals("Here are the task(s) in your list:\n" + "  1. [T][ ] Read book",
                t.taskListToString());
    }

    @Test
    public void findTasks_matchingCase() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new ToDo("Read book"));
        t.addTask(new ToDo("Write book review"));
        t.addTask(new ToDo("Homework"));
        assertEquals(2, t.findTasks("book").size());
    }

    @Test
    public void findTasks_notMatchingCase() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new ToDo("Read book"));
        t.addTask(new ToDo("Write book review"));
        t.addTask(new ToDo("Homework"));
        assertEquals(0, t.findTasks("Book").size());
    }
}
