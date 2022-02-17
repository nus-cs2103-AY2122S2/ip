package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents a Storage object that tracks and stores the tasks that the user has keyed in
 * previous interactions with the GUI.
 */
public class Storage {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    protected String directoryPath;
    protected String filePath;
    private TextUi ui;

    /**
     * Instantiates a storage object given a directory path and a file path.
     * The directory path and file path provides the information of where the
     * storage file should be stored.
     *
     * @param directoryPath Directory path to storage file.
     * @param filePath File path to storage file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Default constructor of a Storage object.
     * This constructor does not take in any arguments.
     */
    public Storage() {}


    /**
     * Method that takes in a list of tasks and writes it into the storage file.
     * Throws an exception if the program is unable to find the file that it needs
     * to write the tasks to.
     *
     * @throws DukeException if the file cannot be found in the directory path provided.
     */
    public static void writeToDukeFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task: tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            throw new StorageException("FILE_NOT_FOUND");
        }
    }

    /**
     * Method that reads from the file and outputs a list of tasks that have been read from the storage file.
     *
     * @return List of tasks that have been stored in the storage file object.
     * @throws DukeException if the program is unable to read content from duke file or if the file does not exist
     * at the location specified.
     */
    public ArrayList<Task> readFromDukeFile() throws DukeException {
        createDirectory();
        try {
            Scanner readFile = new Scanner(new File(this.filePath));
            while (readFile.hasNextLine()) {
                Task taskToAdd;
                String taskData = readFile.nextLine();
                String[] taskDataArray = taskData.split(",");
                String taskInput = taskDataArray[0].toUpperCase(Locale.ROOT);

                taskToAdd = parseTaskInput(taskInput, taskDataArray);
                if (taskToAdd == null) {
                    throw new StorageException("FILE_READ_ERROR");
                }

                if (taskDataArray[1].equals("1")) {
                    taskToAdd.markIsDone();
                }
                tasks.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            throw new StorageException("FILE_NOT_FOUND");
        }
        return tasks;
    }


    /**
     * Returns a task based on the stored file format of the task.
     * Throws an exception if the file is corrupted or if the tasks have been stored
     * in a format that is unreadable by the parser.
     *
     * @param taskInput Stored file format of the task.
     * @param taskDataArray Array containing the information of the task.
     * @return A task that is generated based on the information in the taskArray as well as
     * the task input.
     * @throws DukeException if the parser is unable to convert the file format of the task
     * to a Task object.
     */
    private Task parseTaskInput(String taskInput, String[] taskDataArray) throws DukeException {
        switch (taskInput) {
        case "E":
            if (taskDataArray.length == 4) {
                return new Event(taskDataArray[2], taskDataArray[3]);
            } else {
                return null;
            }
        case "T":
            if (taskDataArray.length == 3) {
                return new Todo(taskDataArray[2]);
            } else {
                return null;
            }
        case "D":
            if (taskDataArray.length == 4) {
                return new Deadline(taskDataArray[2], taskDataArray[3]);
            } else {
                return null;
            }
        default:
            assert false : taskInput;
            return null;
        }
    }


    /**
     * Method that creates a directory for the storage file if it does not exist.
     * Throws an exception if there is already an existing directory that contains
     * the file.
     *
     * @throws StorageException if there is an error that occurs when trying to create the
     * directory.
     */
    public void createDirectory() throws DukeException {
        boolean isDirectoryExists = new File(this.directoryPath).exists();
        boolean isFileExists = new File(this.filePath).exists();
        try {
            if (!isDirectoryExists) {
                Files.createDirectories(Path.of(this.directoryPath));
                File dukeFile = new File(this.filePath);
                if (!dukeFile.createNewFile()) {
                    throw new IOException();
                }
            } else {
                if (!isFileExists) {
                    File dukeFile = new File(this.filePath);
                    if (!dukeFile.createNewFile()) {
                        throw new IOException();
                    }
                }
            }
        } catch (Exception e) {
            throw new StorageException("DIRECTORY_CREATION_ERROR");
        }
    }

    /**
     * Returns a string that is shown to the user when the application first launches.
     * It contains all the tasks that have been saved in duke from the user's previous
     * interactions with the Duke GUI.
     *
     * @return A string containing all the list of tasks that has been stored in Duke.
     */
    public String getStorageTasks() {
        String tasksPreview = tasks.size() > 0 ? TaskList.listTasks(tasks) : "-";
        return "Here is the list of tasks we have saved! \n"
                + tasksPreview;
    }
}

