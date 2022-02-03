package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Utility class to load a saved file, or save to a file.
 */
public class Storage {

    private static String filePath;

    /**
     * Creates a new instance of Storage that loads the history from a specific path
     * or creates a new file for saving tasks
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads the current content of the file or create a new file for saving the list of tasks
     */
    public Scanner load() throws DukeException {
        try {
            File file = new File(filePath);
            return new Scanner(file);
        } catch (IOException e) {
            File fileDir = new File("src/main/java/duke/data");
            fileDir.mkdirs();
            File fileToCreate = new File(fileDir, "duke.txt");
            Scanner sc = null;
            try {
                new FileWriter(fileToCreate);
                sc = new Scanner(fileToCreate);
            } catch (IOException error) {
                throw new DukeException(error.getMessage());
            }
            return sc;
        }
    }

    /**
     * Appends new tasks into saved file
     *
     * @param content Task to be added to saved file
     */
    public static void append(String content) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception thrown:" + e);
        }
    }

    /**
     * Save all tasks into a file
     */
    public static void saveToFile(ArrayList<Task> taskList) {
        String result = "";
        for (Task task : taskList) {
            result = result.concat(task.toSave() + "\n");
        }

        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception thrown:" + e);
        }
    }
}
