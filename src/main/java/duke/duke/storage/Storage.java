package duke.storage;

import duke.info.exception.NoPreviousSaveException;
import duke.info.task.Deadline;
import duke.info.task.Event;
import duke.info.task.Task;
import duke.info.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    Task parseTask(String fileEntry) {
        String[] splitEntry = fileEntry.split("|");
        switch(splitEntry[0]) {
            case "deadline":
                return new Deadline(splitEntry[2], splitEntry[3], splitEntry[1].equals("1") ? true : false);
            case "event":
                return new Event(splitEntry[2], splitEntry[3], splitEntry[1].equals("1") ? true : false);
            default:
                return new Todo(splitEntry[2] ,splitEntry[1].equals("1") ? true : false);
        }
    }

    public ArrayList<Task> load() throws NoPreviousSaveException {
        try {
            Scanner scanner = new Scanner(this.file);
            ArrayList<Task> taskList = new ArrayList<>();
            while (scanner.hasNext()) {
                taskList.add(parseTask(scanner.nextLine()));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

}
