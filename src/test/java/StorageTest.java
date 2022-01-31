import dukeclasses.DukeException;
import dukeclasses.Storage;
import dukeclasses.Task;
import dukeclasses.ToDo;
import dukeclasses.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void loadTest_fileDoesNotExist_createEmptyFile() {
        String filePath = "dataTest.txt";
        if (Files.exists(Paths.get(filePath))) {
            try {
                Files.delete(Paths.get(filePath));
            } catch (IOException errorMessage) {
                fail();
            }
        }
        File file = new File(filePath);
        Storage storage = new Storage(filePath);
        try {
            ArrayList<Task> tasks = storage.load();
            assertEquals(0, tasks.size());
        } catch (DukeException errorMessage) {
            fail();
        }

        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException errorMessage) {
            fail();
        }
    }

    @Test
    public void updateStorage_newTaskArrayList_containTasks() {
        String filePath = "dataTest.txt";
        File file = new File(filePath);
        if (Files.exists(Paths.get(filePath))) {
            try {
                Files.delete(file.toPath());
            } catch (IOException errorMessage) {
                fail();
            }
        }

        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new ToDo("sleep"));
        tasks.add(new Event("run", LocalDate.parse("2021-10-10")));
        tasks.add(new Event("do homework", LocalDate.parse("2021-12-05")));

        Storage storage = new Storage(filePath);

        try {
            storage.updateStorage(tasks);
        } catch (DukeException errorMessage) {
            fail();
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException errorMessage) {
            fail();
        }

        for (int i = 0; i < 3; i++) {
            String expectedOutput =  String.format("    %s", tasks.get(i).identify());
            assertEquals(sc.nextLine(), expectedOutput);
        }

        sc.close();

        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException errorMessage) {
            fail();
        }
    }

    @Test
    public void appendToStorage_newTask_listOf1Task() {
        String filePath = "dataTest.txt";
        File file = new File(filePath);
        if (Files.exists(Paths.get(filePath))) {
            try {
                Files.delete(file.toPath());
            } catch (IOException errorMessage) {
                fail();
            }
        }

        Storage storage = new Storage(filePath);
        Event event = new Event("go to school", LocalDate.parse("2021-09-24"));
        try {
            storage.appendToStorage(event);
        } catch (DukeException errorMessage) {
            fail();
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException errorMessage) {
            fail();
        }
        String expectedOutput =  String.format("    %s", event.identify());
        assertEquals(sc.nextLine(), expectedOutput);
        sc.close();
    }

}
