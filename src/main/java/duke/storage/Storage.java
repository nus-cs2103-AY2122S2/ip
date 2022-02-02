package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.UserInput;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This is a Storage class used in Duke.
 * The responsibility of this class is to make a local path to Read or Write to/from a .txt file.
 * The .txt file will save the tasks recorded down by Duke.
 */
public class Storage {
    private static final String path = Paths.get("").toAbsolutePath() + "/data/";
    private static final File data = new File(path + "duke.txt");
    private final File directory = new File(path);

    /**
     * Makes a "duke.txt" if it does not exist in the user's computer.
     */
    public void makeFile() {
        if (!directory.exists()) {
            try {
                directory.mkdir();
            } catch (Exception e) {
                System.out.println("OOPS!!! Directory cannot be created D:");
            }
        } else if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS!!! File cannot be created D:");
            }
        }
    }

    /**
     * Load the tasks from "duke.txt".
     *
     * @param taskList TaskList to store all the tasks from the txt file into.
     */
    public void loadFile(TaskList taskList) {
        try {
            FileInputStream fileInputStream = new FileInputStream(data);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                UserInput userInput = Parser.parseTask(input);
                taskList.addTaskWithoutMessage(userInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("OOPS!!! File not found D:");
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Write to "duke.txt" whenever necessary to save the progress.
     *
     * @param list An ArrayList to write into the txt file.
     * @throws IOException If the format of the task is invalid.
     */
    public void writeToFile(ArrayList<Task> list) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(data, false);
        for (Task task : list) {
            String toWrite = task.toString() + '\n';
            fileOutputStream.write(toWrite.getBytes(StandardCharsets.UTF_8));
        }
    }
}
