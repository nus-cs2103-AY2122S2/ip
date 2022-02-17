package chibot.storage;

import chibot.exception.ChiException;
import chibot.task.Deadline;
import chibot.task.Event;
import chibot.task.Task;
import chibot.task.Todo;
import chibot.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads and stores tasks from given data file to and from application.
 */
public class Storage {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    /** The relative file path of the data file */
    private final String dataFilePath;

    /** The relative path of the directory containing the data file */
    private final String dataFolderPath;

    /**
     * Constructor of the class.
     *
     * @param path The relative path of the data file to use for storage of tasks.
     */
    public Storage(String path) {
        this.dataFilePath = path;
        String[] rPaths = path.split("/");
        StringBuilder s = new StringBuilder("./");
        for (int i = 0; i < rPaths.length - 1; i++) {
            s.append(rPaths[i]);
        }
        this.dataFolderPath = s.toString();
    }

    /**
     * Deciphers a task in the data file.
     *
     * @param task A single task stored in the data file.
     * @return A Task instance of the task read from the data file.
     */
    public Task convertFileDataToTask(String task) {
        String[] splitTask = task.split("\\|");
        assert splitTask.length > 2 : "There is something wrong with this stored task";
        for (int i = 0; i < splitTask.length; i++) {
            splitTask[i] = splitTask[i].trim();
        }
        if (splitTask[0].equals("T")) {
            if (validateDataFileTodo(splitTask)) {
                return new Todo(splitTask[2], splitTask[1].equals("1"));
            }
        } else if (splitTask[0].equals("D")) {
            if (validateDataFileDeadline(splitTask)) {
                return new Deadline(splitTask[2], LocalDate.parse(splitTask[3],
                        DateTimeFormatter.ofPattern(DATE_FORMAT)),
                        LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern(TIME_FORMAT)),
                        splitTask[1].equals("1"));
            }
        } else {
            if (validateDataFileEvent(splitTask)) {
                return new Event(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern(DATE_FORMAT)),
                        LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern(TIME_FORMAT)),
                        LocalTime.parse(splitTask[5], DateTimeFormatter.ofPattern(TIME_FORMAT)),
                        splitTask[1].equals("1"));
            }
        }
        return null;
    }

    /**
     * Checks if the todo was corrupted in the data file.
     *
     * @param tokens Array of words separated by | delimiter in the data file entry.
     * @return A boolean of whether the todo was corrupted.
     */
    public boolean validateDataFileTodo(String[] tokens) {
        if (tokens.length != 3) {
            return false;
        } else if (!(tokens[1].equals("0") || tokens[1].equals("1"))) {
            return false;
        } else return !tokens[2].trim().equals("");
    }

    /**
     * Checks if the deadline was corrupted in the data file.
     *
     * @param tokens Array of words separated by | delimiter in the data file entry.
     * @return A boolean of whether the deadline was corrupted.
     */
    public boolean validateDataFileDeadline(String[] tokens) {
        if (tokens.length != 5) {
            return false;
        } else if (!(tokens[1].equals("0") || tokens[1].equals("1"))) {
            return false;
        } else if (tokens[2].trim().equals("")) {
            return false;
        } else {
            try {
                LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalTime.parse(tokens[4], DateTimeFormatter.ofPattern(TIME_FORMAT));
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }

    }

    /**
     * Checks if the event was corrupted in the data file.
     *
     * @param tokens Array of words separated by | delimiter in the data file entry.
     * @return A boolean of whether the event was corrupted.
     */
    public boolean validateDataFileEvent(String[] tokens) {
        if (tokens.length != 6) {
            return false;
        } else if (!(tokens[1].equals("0") || tokens[1].equals("1"))) {
            return false;
        } else if (tokens[2].trim().equals("")) {
            return false;
        } else {
            try {
                LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalTime.parse(tokens[4], DateTimeFormatter.ofPattern(TIME_FORMAT));
                LocalTime.parse(tokens[5], DateTimeFormatter.ofPattern(TIME_FORMAT));
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }

    /**
     * Extracts the tasks stored in the data file.
     *
     * @return An ArrayList of Task instances interpreted from data file.
     * @throws IOException If there are interruptions while accessing the file.
     * @throws ChiException If the file or its directory are not found.
     */
    public ArrayList<Task> load() throws IOException, ChiException {
        try {
            ArrayList<Task> loadedTasks = new ArrayList<>();
            File dataFile = new File(dataFilePath);
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String task = s.nextLine();
                Task t = convertFileDataToTask(task);
                if (t == null) {
                    continue;
                }
                loadedTasks.add(t);
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            File dataFolder = new File(dataFolderPath);
            if (!dataFolder.isDirectory()) {
                dataFolder.mkdir();
            }
            File newDataFile = new File(dataFilePath);
            newDataFile.createNewFile();
            throw new ChiException("File not found");
        }
    }

    /**
     * Updates tasks on the data file.
     *
     * @param task The Task instance to be updated.
     * @param tl The TaskList storing all Task instances.
     * @param type The command used.
     * @throws IOException If there are interruptions while accessing the file.
     */
    public void updateFile(Task task, TaskList tl, String type) throws IOException, ChiException {
        FileWriter fw;
        switch(type) {
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            fw = new FileWriter(dataFilePath);
            fw.write(tl.getTaskStore());
            fw.close();
            break;
        case "event":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "todo":
            fw = new FileWriter(dataFilePath, true);
            fw.write(task.convertToFileFormat() + "\n");
            fw.close();
            break;
        default:
            throw new ChiException("Something went wrong while writing to file nyan!");
        }
    }

}
