package duke.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage is a utility class representing the file path on the disk
 * storing the user's list of tasks.
 * <p/>
 * This class contains functions to load and save tasks to the disk.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance with the given file path.
     *
     * @param filePath The file path on the disk for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of tasks stored in the file path of this storage.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * If the file does not exist, returns an empty ArrayList.
     * @throws DukeException If file input cannot be read of file input has wrong format.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String taskInput;
            ArrayList<Task> readTasks = new ArrayList<Task>();
            while ((taskInput = fileReader.readLine()) != null) {
                String[] splitTaskInput = taskInput.split(" \\| ");
                Task newTask;
                if (splitTaskInput[0].equals("T")) {
                    newTask = new ToDo(splitTaskInput[2]);
                } else if (splitTaskInput[0].equals("D")) {
                    LocalDateTime dateTime = Parser.parseDateTime(splitTaskInput[3]);
                    newTask = new Deadline(splitTaskInput[2], dateTime);
                } else if (splitTaskInput[0].equals("E")) {
                    LocalDateTime dateTime = Parser.parseDateTime(splitTaskInput[3]);
                    newTask = new Event(splitTaskInput[2], dateTime);
                } else {
                    throw new DukeException("Error: duke.task.Task type is not T,D or E in file\n");
                }
                int i = Integer.parseInt(splitTaskInput[1]);
                if (i == 1) {
                    newTask.mark(true);
                }
                readTasks.add(newTask);
            }
            return readTasks;
        } catch (IOException e) {
            throw new DukeException("Error: File input cannot be read\n" + e.getMessage());
        } catch (NumberFormatException e) {
            throw new DukeException("Error: isDone field is not indicated by 0 or 1 in file\n" + e.getMessage());
        }
    }

    /**
     * Stores an ArrayList of tasks to the file path of this storage.
     *
     * @param tasks The ArrayList of tasks to be stored.
     * @throws DukeException If the contents cannot be written to the specified file.
     */
    public void saveFileData(ArrayList<Task> tasks) throws DukeException {
        try {
            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectories(Paths.get("data/"));
                Files.createFile(Paths.get(filePath));
            }

            BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath), Charset.forName("UTF-8"));
            for (Task t : tasks) {
                bw.append(t.getFileSaveFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file\n" + e.getMessage());
        }
    }
}
