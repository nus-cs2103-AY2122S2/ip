package duke.component;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.Scanner;

import duke.task.Task;

/**
 * A class to handle reading and storing data.
 */
public class Storage {
    public static final String ROOT = System.getProperty("user.dir");
    public static final String FOLDER = "data";
    public static final String FILE_NAME = "duke.txt";
    public static final Path PATH_TO_STORAGE_FILE = Paths.get(ROOT, FOLDER, FILE_NAME);

    /**
     * Create duke.txt file if it does not exist.
     * @throws IOException if file not found
     */
    public static void createFile() throws IOException {
        File file = new File(PATH_TO_STORAGE_FILE.toString());
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Read date from duke.txt.
     *
     * @return ArrayList contains Task
     * @throws IOException if file not found
     */
    public ArrayList<Task> readDataFromFile() throws IOException{
        if (Files.notExists(PATH_TO_STORAGE_FILE)) {
            createFile();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATH_TO_STORAGE_FILE.toString());
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            Task task = Parser.retrieveTaskFromStoredData(sc.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Write the task list to duke.txt.
     *
     * @param taskList TaskList class
     * @throws IOException if file not found
     */
    public void writeTaskToFile(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(PATH_TO_STORAGE_FILE.toString());
        Parser parser = new Parser();
        for (Task task: taskList.getTasks()) {
            fw.write(parser.formatTaskToStore(task));
        }
        fw.close();
    }
}
