import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.DeadlineTask;
import taskmaster.task.TodoTask;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

public class StorageTest {
    /** The file path of the data file. **/
    private static final String FILE_PATH = "./data/Duke.txt";
    private Storage storage = new Storage(FILE_PATH);
    private TaskList taskList = new TaskList();

    private void setUpParameters() {
        StorageTest testStorage = new StorageTest();
        try {
            testStorage.storage.loadFile(taskList);
        } catch (TaskmasterExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    private void populateTaskList() {
        taskList.add(new TodoTask("Donkey Kong"));
        LocalDateTime currentTime = LocalDateTime.now();
        taskList.add(new DeadlineTask("Supper", currentTime));
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
            this.storage.updateList(taskList);
            String contentInFile = "";
            String st = "";
            BufferedReader br = new BufferedReader(new FileReader(fileToBeLoaded));
            while ((st = br.readLine()) != null) {
                contentInFile = contentInFile.concat(st + "\n");
            }
            String expectedString = taskList.listTasksInTextFormat() + "\n";
            assertEquals(expectedString, contentInFile);
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }

    }

}

