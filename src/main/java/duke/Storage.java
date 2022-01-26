package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to abstract and encapsulate file interactions for Duke.
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
        // Create file if it doesn't exist
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
        }
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
        fileWriter.write(taskList.tasksFileSaveFormat());
        fileWriter.close();
    }

    /**
     * Loads Tasks from file
     *
     * @return TaskList populated with data from file
     * @throws IOException file access error
     * @throws DukeException when format error is present in file
     */
    public TaskList loadTasks() throws IOException, DukeException {
        createIfNotExist();

        try {
            Scanner fileReader = new Scanner(this.file);

            TaskList taskList = new TaskList();
            for (int lineCount = 1; fileReader.hasNextLine(); lineCount++) {
                try {
                    Task task = Task.parseFileSaveFormat(fileReader.nextLine());
                    taskList.addTask(task);
                } catch (DukeException e) {
                    throw new DukeException(String.format("Wrong format in line %d", lineCount));
                }
            }

            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }
}
