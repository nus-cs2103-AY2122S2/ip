package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for writing and loading the list of tasks to and from the user's memory.
 */
public class Storage {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    /**
     * Writes the list of tasks to {PROJECT_ROOT}/data/duke.txt
     *
     * @param tasks The list of tasks to be written.
     */
    public void save(TaskList tasks) {
        try {
            String path = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the list of tasks from {{PROJECT_ROOT}/data/duke.txt}
     *
     * @return The saved list of tasks.
     */
    public TaskList load() {
        Path dataFolder = Path.of(PROJECT_ROOT, "data");
        if (!Files.exists(dataFolder)) { //initialize data directory if not present
            new File(dataFolder.toString()).mkdir();
        }
        try {
            String path = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { //if duke.txt does not exist or is weird
            return new TaskList();
        }
    }
}
