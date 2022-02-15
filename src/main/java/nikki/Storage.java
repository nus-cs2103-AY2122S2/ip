package nikki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import nikki.task.Task;
import nikki.task.TaskList;

/**
 * Class to abstract and encapsulate file interactions for Nikki.
 *
 * File format:
 * tag||<0 or 1>||name[||additional arguments...]
 */
public class Storage {
    private String filename;
    private File file;

    /**
     * Instantiates a FileManager
     *
     * @param filename path to file of interaction
     */
    public Storage(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    /**
     * Checks the existence of the file, creates it if it doesn't exist
     *
     * @throws IOException cannot create directories/file
     */
    private void createIfNotExist() throws IOException {
        if (this.file.exists()) {
            return;
        }

        this.file.getParentFile().mkdirs();
        this.file.createNewFile();
    }

    /**
     * Saves Tasks to file in a standard format
     *
     * @param taskList list of Tasks to save
     * @throws IOException file access error
     */
    public void saveTasks(TaskList taskList) throws IOException {
        createIfNotExist();
        FileWriter fileWriter = new FileWriter(this.filename, false);
        fileWriter.write(taskList.listFileSaveFormat());
        fileWriter.close();
    }

    /**
     * Loads Tasks from file
     *
     * @return TaskList populated with data from file
     * @throws IOException file access error
     * @throws NikkiException when format error is present in file
     */
    public TaskList loadTasks() throws IOException, NikkiException {
        createIfNotExist();

        try {
            Scanner fileReader = new Scanner(this.file);
            return loadTaskListFromScanner(fileReader);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Loads a TaskList from a Scanner object.
     * Each line of the Scanner should represent a Task
     *
     * @param scanner Scanner object to load from
     * @return loaded TaskList
     * @throws NikkiException when format error is present in a Scanner line
     */
    private TaskList loadTaskListFromScanner(Scanner scanner) throws NikkiException {
        TaskList taskList = new TaskList();
        for (int lineCount = 1; scanner.hasNextLine(); lineCount++) {
            try {
                Task task = Task.parseFileSaveFormat(scanner.nextLine());
                taskList.addTask(task);
            } catch (NikkiException e) {
                throw new NikkiException(String.format("Wrong format in line %d", lineCount));
            }
        }

        return taskList;
    }
}
