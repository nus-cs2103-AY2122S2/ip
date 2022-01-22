package duke.storage;

import duke.info.exception.DukeException;
import duke.info.exception.NoPreviousSaveException;
import duke.info.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;
    private final String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    Task parseTask(String fileEntry) throws DukeException {
        String[] splitEntry = fileEntry.split("\\|");
        String type = splitEntry[0];
        System.out.println(type);
        String action = splitEntry[2];
        boolean isComplete = splitEntry[1].equals("0") ? false : true;
        switch(type) {
            case "todo":
                return new Todo(action, isComplete);
            case "deadline":
                String deadlineDate = splitEntry[3];
                return new Deadline(action, deadlineDate, isComplete);
            case "event":
                String eventDate = splitEntry[3];
                return new Event(action, eventDate, isComplete);
            default:
                throw new DukeException("Error while parsing save file");
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
        } catch (DukeException e) {
            return new ArrayList<>();
        }
    }

    public void save(Calendar toSave) throws IOException {
        System.out.println("Saving");
        FileWriter fw = new FileWriter(filePath);
        fw.write(toSave.saveFormat());
        fw.close();
    }
}
