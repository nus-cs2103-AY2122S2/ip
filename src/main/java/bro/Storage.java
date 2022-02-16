package bro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bro.exceptions.BroException;
import bro.tasks.Task;

public class Storage {
    private String filePath = "";
    private String dirPath = "";
    private File file;

    public Storage() { }

    /**
     * Constructor for Storage class.
     *
     * @param path The path of the file to save and load from.
     */
    public Storage(String path) {
        String fileSeparator = "/";
        filePath = System.getProperty("user.home") + fileSeparator + path;
        String dir = path.substring(0, path.lastIndexOf("/"));
        dirPath = System.getProperty("user.home") + fileSeparator + dir;
    }

    /**
     * Loads a TaskManager from the information saved in the file.
     *
     * @return A TaskManager containing all tasks from the saved file.
     * @throws BroException If an error is encountered when accessing the saved file.
     */
    public TaskManager loadTaskManagerFromFile() throws BroException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        file = new File(filePath);
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String fileInput = scanner.nextLine();
                    try {
                        Task t = Parser.parseToTaskFromFile(fileInput);
                        tasks.add(t);
                    } catch (BroException exception) {
                        continue;
                    }
                }
                return new TaskManager(tasks);
            } else {
                throw new BroException("Unable to read Task List from file!");
            }
        } catch (SecurityException | IOException exception) {
            throw new BroException("Unable to read Task List from file!");
        }
    }

    /**
     * Saves the contents of the current task manager to a file to be loaded later.
     *
     * @param taskManager The task manager to be saved.
     * @return True if task manager is saved successfully.
     * @throws BroException If error is encountered during saving.
     */
    public boolean saveTaskManager(TaskManager taskManager) throws BroException {

        try {

            file = new File(filePath);

            if (!dirExists()) {
                boolean dirCreated = createDir();
                if (!dirCreated) {
                    throw new IOException("Unable to create directory!");
                }
            }

            FileWriter writer = new FileWriter(file);

            for (Task task: taskManager.getTaskList()) {
                writer.write(Parser.parseToFileFromTask(task) + "\n");
            }

            writer.close();
            return true;
        } catch (IOException exception) {
            throw new BroException("Unable to save to disk!");
        }
    }

    /**
     * Checks if the directory of the file exists.
     *
     * @return True if the directory exists.
     */
    public boolean dirExists() {
        File dir = new File(dirPath);
        return dir.exists();
    }

    /**
     * Creates the directory that the file will be saved in.
     *
     * @return True if the directory is successfully created, false otherwise.
     */
    public boolean createDir() {
        File dir = new File(dirPath);
        return dir.mkdir();
    }

    public String getFullPath() {
        return this.filePath;
    }
}
