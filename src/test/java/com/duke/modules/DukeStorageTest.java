package com.duke.modules;

import com.duke.tasks.Task;
import com.duke.tasks.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DukeStorageTest {
    @Test
    public void saveFileTest() {
        boolean passedTest = false;
        Storage testStorage = Storage.getInstance();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("mop floor"));
        try {
            testStorage.saveList(taskList);
            passedTest = true;
        } catch (IOException e) {
            passedTest = false;
        } finally {
            assertEquals(passedTest, true);
        }
    }

    @Test
    public void loadFileTest() {
        Storage testStorage = Storage.getInstance();
        ArrayList<Task> testList = new ArrayList<>();
        testStorage.loadList(testList);
        assertEquals(testList.get(0).getClass().getSimpleName(), "Todo");
        assertEquals(testList.get(0).getDescription().trim(), "mop floor");
    }

    @AfterAll
    public static void deleteFile() {
        Storage testStorage = Storage.getInstance();
        File file = new File(testStorage.getDirectoryPath() + "listData.txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
