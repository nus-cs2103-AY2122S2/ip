package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Represents a class meant for Unit Testing Duke.
 */
public class DukeTest {

    private static DukeStorage dummyStorage = new DukeStorage();
    private DukeContact dummyContact = new DukeContact();
    private DukeHistory dummyHistory = new DukeHistory();
    private DukeUi dummyUi = new DukeUi();
    private DukeParser dummyParser = new DukeParser(dummyContact, dummyHistory, dummyStorage, dummyUi);

    @BeforeAll
    public static void setUp() {
        try {
            dummyStorage.startup("test", "dummyDuke.txt");
        } catch (IOException e1) {
            fail("startup failed, dummyDuke.txt was not created\n");
        } catch (NullPointerException e2) {
            fail("filePath was not stored in dummyStorage\n");
        }
    }

    @Test
    public void startup_createNewFile_exists() {
        File dummyFile = new File(dummyStorage.getFilePath());
        if (dummyFile.exists()) {
            System.out.println("startup SUCCEEDED in creating dummyDuke.txt\n");
        } else {
            fail("startup FAILED in creating dummyDuke.txt\n");
        }
    }

    @Test
    public void restore_editCreatedFile_dataRestored() {
        try {
            File dummyFilePath = new File(dummyStorage.getFilePath());
            FileWriter fw = new FileWriter(dummyFilePath);
            fw.append("T>0>duke test\n")
                    .append("D>1>CA1>Sunday>7pm\n")
                    .append("E>0>Team Meeting>Sunday>10am-12pm\n");
            fw.close();
            dummyStorage.restore(dummyHistory);
            assertEquals(3,
                    dummyHistory.getSize(),
                    "restore SUCCEEDED in loading data into dummyHistory\n");
        } catch (IOException e) {
            fail("restore FAILED to locate, edit and read from dummyTest.txt\n");
        }
    }

    @Test
    public void markAndUnMark_editCreatedFile_dataChanged() {
        try {
            String[] markCall = {"mark", "1"};
            String[] unmarkCall = {"unmark", "2"};
            dummyStorage.restore(dummyHistory);
            dummyParser.markCommand(markCall);
            dummyParser.unmarkCommand(unmarkCall);
            dummyStorage.update(dummyHistory);
            Path dummyFilePath = Paths.get(dummyStorage.getFilePath());
            String entry1 = Files.readAllLines(dummyFilePath).get(0);
            String entry2 = Files.readAllLines(dummyFilePath).get(1);
            if (entry1.equals("T>1>duke test")) {
                System.out.println("mark SUCCEEDED in marking entry 1\n");
            } else {
                fail("mark FAILED in marking entry 1\n");
            }
            if (entry2.equals("D>0>CA1>Sunday>7pm")) {
                System.out.println("unmark SUCCEEDED in un-marking entry 2\n");
            } else {
                fail("unmark FAILED in un-marking entry 2\n");
            }
        } catch (FileNotFoundException e1) {
            fail("restore FAILED to locate dummyText.txt\n");
        } catch (IOException e) {
            fail("test FAILED to read from dummyTest.txt\n");
        }
    }

    @AfterAll
    public static void tearDown() {
        File dummyFile = new File(dummyStorage.getFilePath());
        if (dummyFile.exists()) {
            if (dummyFile.delete()) {
                System.out.println("this is the end of the test\n");
            } else {
                System.out.println("automatic deletion of dummyTest.txt FAILED\n");
            }
        }
    }
}
