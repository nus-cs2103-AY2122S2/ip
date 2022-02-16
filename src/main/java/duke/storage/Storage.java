package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


public class Storage {
    public static final String DEFAULT_STORAGE_DIRECTORY = "./data/";
    public static final String DEFAULT_STORAGE_FILEPATH = DEFAULT_STORAGE_DIRECTORY + "tasks.txt";

    public final Path path;

    public Storage() throws DukeException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Creates a Storage instance with a filename.
     *
     * @param fileName The filename of the saved file.
     * @throws DukeException If the path is invalid, it throws
     * a DukeException.
     */
    public Storage(String fileName) throws DukeException {
        path = Paths.get(DEFAULT_STORAGE_DIRECTORY + fileName);
        if (isValidPath(path)) {
            throw new DukeException("duke.storage.Storage file should end with '.txt'");
        }
    }

    /**
     * Loads all tasks to Duke.
     *
     * @return An array list of task.
     * @throws DukeException If BufferedReader failed, it throws
     * a DukeException.
     */
    public ArrayList<Task> loadAllTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        if (isValidPath(path)) {
            return taskList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            String strCurrentLine;
            Task currentTask;

            while ((strCurrentLine = reader.readLine()) != null) {
                String[] taskInput = strCurrentLine.split(" \\| ");
                switch (taskInput[0]) {
                case "T":
                    currentTask = new Todo(taskInput[2]);
                    break;
                case "D":
                    currentTask = new Deadline(taskInput[2], Parser.parseDateTime(taskInput[3]));
                    break;
                case "E":
                    currentTask = new Event(taskInput[2], Parser.parseDateTime(taskInput[3]));
                    break;
                default:
                    throw new DukeException("Invalid task type");
                }

                if (Integer.parseInt(taskInput[1]) == 1) {
                    currentTask.markAsDone();
                }

                taskList.add(currentTask);
            }

            reader.close();

            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("No file in storage.");
        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    /**
     * Saves all tasks as a file.
     *
     * @param tasks The tasks for saving.
     * @throws DukeException If the tasks failed to save, it throws
     * a DukeException.
     */
    public void saveAllTasks(TaskList tasks) throws DukeException {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(Paths.get(DEFAULT_STORAGE_DIRECTORY));
                Files.createFile(path);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));

            for (int i = 0; i < tasks.getSize(); i++) {
                writer.append(tasks.getTask(i).getSaveFormat());
                writer.append('\n');
            }

            writer.close();

        } catch (IOException ioe) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    private static boolean isValidPath(Path filePath) {
        return !filePath.toString().endsWith(".txt");
    }
}
