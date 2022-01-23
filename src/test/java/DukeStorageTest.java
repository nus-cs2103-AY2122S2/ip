import duke.io.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeStorageTest {
    @Test
    public void save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask(new ToDoTask("New Todo Task"));
        tl.addTask(new DeadlineTask("New Deadline Task", LocalDate.parse("11/1/1999", formatter)));
        tl.addTask(new EventTask("New Event Task", LocalDate.parse("11/1/1999", formatter)));
        Storage.saveFile("data", "Duke.txt", tl.getList());
        File f = new File("data/Duke.txt");
        assertEquals(true, f.exists());
        try{
            FileInputStream fs = new FileInputStream("data/Duke.txt");
            Scanner sc = new Scanner(fs);
            String ss = "";
            while (sc.hasNextLine()) {
                ss += sc.nextLine();
            }
            sc.close();
            assertEquals("T,F,New Todo Task,;D,F,New Deadline Task,11/1/1999;E,F,New Event Task,11/1/1999" ,ss);
        } catch (Exception e){}
    }

    @Test public void load() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask(new ToDoTask("New Todo Task"));
        tl.addTask(new DeadlineTask("New Deadline Task", LocalDate.parse("11/1/1999", formatter)));
        tl.addTask(new EventTask("New Event Task", LocalDate.parse("11/1/1999", formatter)));
        Storage.saveFile("data", "Duke.txt", tl.getList());
        TaskList tl2 = new TaskList();
        Storage.loadFile("data/Duke.txt", tl2);
        for(int i = 0; i < tl.getSize(); i++) {
            assertEquals(tl.getTask(i).getTaskName(), tl2.getTask(i).getTaskName());
        }
    }
}
