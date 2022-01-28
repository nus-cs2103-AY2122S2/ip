import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import taskmaster.task.DeadlineTask;
import taskmaster.task.TodoTask;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

public class StorageTest {
    private final Storage STORAGE = new Storage();
    private final TaskList TASKLIST = new TaskList();

    private void setUpParameters() {
        StorageTest testStorage = new StorageTest();
        testStorage.STORAGE.loadFile(TASKLIST);
    }

    private void populateTaskList() {
        TASKLIST.add(new TodoTask("Donkey Kong"));
        LocalDateTime currentTime = LocalDateTime.now();
        TASKLIST.add(new DeadlineTask("Supper", currentTime));
    }


    @Test
    @DisplayName("Directory is created")
    public void testDirectoryCreated() {
        setUpParameters();
        File directory = new File(Paths.get("").toAbsolutePath() + "/data/");
        assertTrue(directory.exists());
    }

    @Test
    @DisplayName("File is created")
    public void testFileCreated() {
        setUpParameters();
        File filename = new File(Paths.get("").toAbsolutePath() + "/data/duke.txt");
        assertTrue(filename.exists());
    }


    @Test
    @DisplayName("Able to write to file")
    public void testWriteFile() {
        setUpParameters();
        populateTaskList();
        try {
            File fileToBeLoaded = new File(Paths.get("").toAbsolutePath() + "/data/duke.txt");
            this.STORAGE.updateList(TASKLIST);
            String contentInFile = "";
            String st = "";
            BufferedReader br = new BufferedReader(new FileReader(fileToBeLoaded));
            while ((st = br.readLine()) != null) {
                contentInFile = contentInFile.concat(st + "\n");
            }
            String expectedString = TASKLIST.listTasksInTextFormat() + "\n";
            assertEquals(expectedString, contentInFile);
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }




    }




}
