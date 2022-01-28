package Duke.util;

import Duke.exception.DukeException;

import Duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public Scanner load() throws DukeException {
        try {
            File file = new File(FILE_PATH);
            return new Scanner(file);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Appends new tasks into saved file
     *
     * @param content Task to be added to saved file
     */
    public static void append(String content) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, true);
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
            FileWriter writer = new FileWriter(FILE_PATH, false);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception thrown:" + e);
        }
    }
}
