package mnsky;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import mnsky.core.Storage;
import mnsky.core.TaskList;
import mnsky.core.Ui;
import org.junit.jupiter.api.Test;

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

    class UiStub extends Ui {
        public UiStub() {
            super();
        }
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new UiStub(), new StorageStub());
        Task newTask = taskList.addTask("ggg ffff hh");

        assertEquals(newTask.getName(), "ggg ffff hh");
        assertEquals(taskList.getListStrings().size(), 1);
        assertEquals(taskList.getListStrings().get(0), "1. " + newTask.toString());
    }
}
