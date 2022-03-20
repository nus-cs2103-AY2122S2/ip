package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores user tasks data.
 */
public class Storage {
    // initializing saver to save todoList tasks to relativePath
    private String fileSeparator = System.getProperty("file.separator");
    private String relativePath = "data" + fileSeparator + "duke.txt";

    /**
     * Constructor of Storage object.
     *
     * @throws IOException
     */
    public Storage() throws IOException {
        File f = new File(this.relativePath);
        // if file does not exist, create file based on filepath
        if (!f.exists()) {
            // create data directory
            Files.createDirectories(Path.of(f.getParent()));

            // create new file
            if (f.createNewFile()) {
                System.out.println(this.relativePath + " file created in the project root directory");
            }
        } else {
            System.out.println(this.relativePath + " already exists in the project root directory");
        }
    }

    /**
     * Saves the task list to disk.
     *
     * @param todoList tasklist to save.
     * @throws IOException If file does not exist.
     */
    public void save(TaskList todoList) throws IOException {
        FileWriter fw = new FileWriter(this.relativePath);
        for (int i = 0; i < todoList.size(); i++) {
            fw.write(todoList.get(i).toStringForSave() + "\n");
        }
        fw.close();
    }

    /**
     * Returns an ArrayList of tasks from disk.
     *
     * @return ArrayList of tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.relativePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> todoList = new ArrayList<>();

        // load task from duke.txt to todoList
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskContents = task.split(" # ");
            String taskType = taskContents[0];
            String taskStatus = taskContents[1];
            String taskDescription = taskContents[2];

            switch (taskType) {
            case "T":
                Todo.loadTodoTask(todoList, taskDescription, taskStatus);
                break;
            case "D":
                Deadline.loadDeadlineTask(todoList, taskDescription, taskStatus, taskContents);
                break;
            case "E":
                Event.loadEventTask(todoList, taskDescription, taskStatus, taskContents);
                break;
            default:
                break;
            }
        }
        return todoList;
    }
}
