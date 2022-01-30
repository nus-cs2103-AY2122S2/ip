package batman.storage;

import batman.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static Path path;

    /**
     * Storage for tasks.
     *
     * @param filePath Path to indicate where the
     *                 storage of the tasks will be located at.
     */
    public Storage(String filePath) {
        //initialize Path object
        String dataPath = System.getProperty("user.dir") + filePath;
        path = Paths.get(dataPath);
        createFile();
    }

    /**
     * Creates file to store tasks.
     */
    public static void createFile() {
        File dataDir = new File("./data");
        dataDir.mkdirs();
        //create file
        try {
            Files.createFile(path);
            System.out.println("File has been created...");

        } catch (IOException e) {
            System.out.println("Loading content...");
        }
    }

    /**
     * Writes existing tasks to file.
     *
     * @param tasks An arrayList of existing tasks.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        Files.write(path, "".getBytes());
        for (Task t : tasks) {
            Files.write(path, t.appendtoFile().getBytes(), StandardOpenOption.APPEND);
        }
    }

    public List<String> load() throws IOException {
        return Files.readAllLines(path);
    }
}