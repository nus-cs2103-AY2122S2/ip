package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.main.DukeException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents a storage system to save the tasks in the hard disk.
 * Ensure data persists in event of Duke closing or crashing.
 *
 */
public class Storage {
    protected String filePath;
    protected File dir;

    /**
     * Constructor for Storage class
     * @param filePath Path for file where data is stored and loaded
     */
    public Storage(String filePath) {
        this.dir = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Load the task stored within hard disk into the Task List.
     *
     * @return List of Tasks.
     * @throws IOException If there are IO errors.
     * @throws DukeException If there is no previously created file for tasks in hard disks.
     */
    public List<Task> load() throws IOException, DukeException {
        if (dir.getParentFile().mkdirs()) { // If no directory exists
            throw new DukeException("No previous save file");
        }
        if (dir.createNewFile()) { // If no save file exists
            throw new DukeException("No previous save file");
        }

        List<Task> loaded = new ArrayList<>();
        Scanner sc = new Scanner(dir);
        while (sc.hasNext()) {
            String encoded = sc.nextLine();
            loaded.add(Task.deserialize(encoded));
        }
        sc.close();
        return loaded;
    }

    /**
     * Save the added file to hard disk.
     * Append the serialized task to the bottom of file in hard disk.
     *
     * @param todo Task to be added to the hard disk.
     * @throws IOException If there is error in writing to disk.
     */
    public void appendFile(Task todo) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(todo.serialize());
        fw.close();
    }

    /**
     * Overwrite the hard disk with state currently in Task List.
     *
     * @param todo TaskList to overwrite hard disk with.
     * @throws IOException If there is error in writing to disk.
     */
    public void overWriteFile(TaskList todo) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        List<String> encodedList = todo.serializedList();
        for (String task : encodedList) {
            fw.write(task);
        }
        fw.close();
    }
}
