package mnsky;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mnsky.core.Storage;
import mnsky.core.TaskList;
import mnsky.task.Task;

public class TaskListTest {
    class StorageStub extends Storage {
        public StorageStub() {
            super("");
        }

        @Override
        public ArrayList<String> readFromDataFile() {
            return new ArrayList<>();
        }
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new StorageStub());
        Task newTask = taskList.addTask("ggg ffff hh");

        assertEquals(newTask.getName(), "ggg ffff hh");
        assertEquals(taskList.getListStrings().size(), 1);
        assertEquals(taskList.getListStrings().get(0), "1. " + newTask.toString());
    }

    @Test
    public void testAddMultipleTasks() {
        TaskList taskList = new TaskList(new StorageStub());
        Task newTask = taskList.addTask("buy new phone");
        Task newDeadline = taskList.addDeadline("homework", "thurs 11 pm");
        Task newEvent = taskList.addEvent("dinner", "sep 22 2016 8 pm");

        assertEquals(newTask.getName(), "buy new phone");
        assertEquals(newDeadline.getName(), "homework");
        assertEquals(newEvent.getName(), "dinner");
        assertEquals(taskList.getListStrings().size(), 3);
        assertEquals(taskList.getListStrings().get(0), "1. " + newTask.toString());
        assertEquals(taskList.getListStrings().get(1), "2. " + newDeadline.toString());
        assertEquals(taskList.getListStrings().get(2), "3. " + newEvent.toString());
    }

    @Test
    public void testDelete() {
        TaskList taskList = new TaskList(new StorageStub());
        taskList.addTask("a");
        Task task2 = taskList.addTask("b");
        Task task3 = taskList.addTask("c");
        taskList.delete("1");

        assertEquals(taskList.getListStrings().size(), 2);
        assertEquals(taskList.getListStrings().get(0), "1. " + task2.toString());
        assertEquals(taskList.getListStrings().get(1), "2. " + task3.toString());
    }

    @Test
    public void testMarkAndUnmark() {
        TaskList taskList = new TaskList(new StorageStub());
        taskList.addTask("a");
        taskList.addTask("b");

        taskList.mark("2");
        assertEquals(taskList.getListStrings().get(0).charAt(7), '?');
        assertEquals(taskList.getListStrings().get(1).charAt(7), 'X');

        taskList.unmark("2");
        assertEquals(taskList.getListStrings().get(0).charAt(7), '?');
        assertEquals(taskList.getListStrings().get(1).charAt(7), '?');

        taskList.unmark("1");
        assertEquals(taskList.getListStrings().get(0).charAt(7), '?');
        assertEquals(taskList.getListStrings().get(1).charAt(7), '?');
    }

    @Test
    public void testFind() {
        TaskList taskList = new TaskList(new StorageStub());
        Task task1 = taskList.addTask("a");
        Task task2 = taskList.addTask("b");
        Task task3 = taskList.addDeadline("book", "idc");
        Task task4 = taskList.addEvent("kjljljkjeljgbsjjj", "idkc either");

        ArrayList<String> foundTasks = taskList.find("a");
        assertEquals(foundTasks.size(), 1);
        assertEquals(foundTasks.get(0), task1.toString());

        foundTasks = taskList.find("b");
        assertEquals(foundTasks.size(), 3);
        assertEquals(foundTasks.get(0), task2.toString());
        assertEquals(foundTasks.get(1), task3.toString());
        assertEquals(foundTasks.get(2), task4.toString());

        foundTasks = taskList.find("c");
        assertEquals(foundTasks.size(), 1);
        assertEquals(foundTasks.get(0), "[MNSKY couldn't find any tasks matching the search term.]");
    }
}
