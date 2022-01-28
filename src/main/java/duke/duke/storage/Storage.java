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

    /**
     * Constructs a Storage object using the save file as specified
     * by filePath. If no previous file exists, a new file will be created
     * at the file location.
     * @param filePath - the path to the file with the previous save
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Returns the Task from the specified fileEntry. This method converts
     * a fileEntry in the save file into a Task that can be used by Duke
     * @param fileEntry - the file entry with the saved task
     * @return - Task represented by the file entry
     * @throws DukeException - if there was an error with parsing the task
     */
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

    /**
     * Returns an ArrayList of tasks that is loaded from the File object.
     * This method reads the File from the File attribute and parses each
     * task using the parseTask() method. The parsed Task is added into an
     * ArrayList and returned by the method.
     * @return - an ArrayList of Tasks from the save file
     * @throws NoPreviousSaveException - if there is no previous save file
     */
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

    /**
     * Saves a file to persistent memory. Saves the specified calendar
     * to the File as specified by the filePath in the constructor
     * @param calendar - the Calendar to be saved to a file
     * @throws IOException - if there was an error saving file using I/O
     */
    public void save(Calendar calendar) throws IOException {
        System.out.println("Saving");
        FileWriter fw = new FileWriter(filePath);
        fw.write(calendar.saveFormat());
        fw.close();
    }
}
