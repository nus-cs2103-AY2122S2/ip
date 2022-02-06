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

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * Represents a Storage object that tracks and stores tasks that the user has keyed in
 * previously
 */
public class Storage {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    protected String directoryPath;
    protected String filePath;

    /**
     * Instantiates a storage object given a directory path and a file path
     * @param directoryPath directoryPath to storage file
     * @param filePath filePath to stroage file
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Instantiates an empty storage object
     */
    public Storage() {}

    /**
     * Method that takes in a list of tasks and writes it into the storage file
     * @throws DukeException if the file cannot be found in the directory
     */
    public static void writeToDukeFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task: tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            throw new DukeException("duke.Duke file cannot be found!! Make sure it exists in the data folder.");
        }
    }

    /**
     * Method that reads from the file and outputs a list of tasks that have been read from the list
     * @return a list of tasks in the storage file object
     * @throws DukeException if unable to read content from duke file/file does not exist
     */
    public ArrayList<Task> readFromDukeFile() throws DukeException {
        createDirectory();
        try {
            Scanner readFile = new Scanner(new File(this.filePath));
            while (readFile.hasNextLine()) {
                Task taskToAdd;
                String taskData = readFile.nextLine();
                String[] taskArray = taskData.split(",");
                String taskInput = taskArray[0].toUpperCase(Locale.ROOT);
                switch (taskInput) {
                case "E":
                    if (taskArray.length == 4) {
                        taskToAdd = new Event(taskArray[2], taskArray[3]);
                        if (taskArray[1].equals("1")) {
                            taskToAdd.markIsDone();
                        }
                        tasks.add(taskToAdd);
                    } else {
                        throw new DukeException("Unable to read file format!");
                    }
                    break;
                case "T":
                    if (taskArray.length == 3) {
                        taskToAdd = new Todo(taskArray[2]);
                        if (taskArray[1].equals("1")) {
                            taskToAdd.markIsDone();
                        }
                        tasks.add(taskToAdd);
                    } else {
                        throw new DukeException("Unable to read file format!");
                    }
                    break;
                case "D":
                    if (taskArray.length == 4) {
                        taskToAdd = new Deadline(taskArray[2], taskArray[3]);
                        if (taskArray[1].equals("1")) {
                            taskToAdd.markIsDone();
                        }
                        tasks.add(taskToAdd);
                    } else {
                        throw new DukeException("Unable to read file format!");
                    }
                    break;
                default:
                    assert false: taskInput;
                    throw new DukeException("No valid tasks found in file!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File cannot be found!");
        }

        return tasks;
    }


    /**
     * Method that creates a directory for the storage file if it does not exist
     * @throws DukeException Error with creating Directory/File
     */
    public void createDirectory() throws DukeException {
        boolean isDirectoryExists = new File(this.directoryPath).exists();
        boolean isFileExists = new File(this.filePath).exists();
        try {
            if (!isDirectoryExists) {
                Files.createDirectories(Path.of(this.directoryPath));
                File dukeFile = new File(this.filePath);
                if (!dukeFile.createNewFile()) {
                    throw new IOException("Unable to create file at specified path. It already exists");
                }
            } else {
                if (!isFileExists) {
                    File dukeFile = new File(this.filePath);
                    if (!dukeFile.createNewFile()) {
                        throw new IOException("Unable to create file at specified path. It already exists");
                    }
                }
            }
        } catch (Exception e) {
            throw new DukeException("Error with file/directory initialization!");
        }
    }


    public String getStorageTasks() {
        if (tasks.size() == 0) {
            return "No tasks in file!";
        } else {
            return "Here is the list of tasks we have saved! \n" + TaskList.listTasks(tasks);
        }
    }
}

