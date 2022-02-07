import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import duke.io.Storage;
import duke.task.TaskList;





public class DukeStorageTest {
    @Test
    public void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask("New Todo Task", false, null, 0);
        tl.addTask("New Deadline Task", false, LocalDate.parse("11/1/1999", formatter), 1);
        tl.addTask("New Event Task", false, LocalDate.parse("11/1/1999", formatter), 2);
        Storage.saveFile("data", "Duke.txt", tl);
        File f = new File("data/Duke.txt");
        assertEquals(true, f.exists());
        try {
            FileInputStream fs = new FileInputStream("data/Duke.txt");
            Scanner sc = new Scanner(fs);
            String ss = "";
            while (sc.hasNextLine()) {
                ss += sc.nextLine();
            }
            sc.close();
            assertEquals("T,F,New Todo Task,;D,F,New Deadline Task,11/1/1999;E,F,New Event Task,11/1/1999", ss);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test public void load() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask("New Todo Task", false, null, 0);
        tl.addTask("New Deadline Task", false, LocalDate.parse("11/1/1999", formatter), 1);
        tl.addTask("New Event Task", false, LocalDate.parse("11/1/1999", formatter), 2);
        Storage.saveFile("data", "Duke.txt", tl);
        TaskList tl2 = new TaskList();
        Storage.loadFile("data/Duke.txt", tl2);
        for (int i = 0; i < tl.getSize(); i++) {
            assertEquals(tl.getTask(i).getTaskName(), tl2.getTask(i).getTaskName());
        }
    }
}
