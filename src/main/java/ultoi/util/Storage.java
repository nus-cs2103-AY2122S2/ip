package ultoi.util;

import ultoi.command.AddCommand;
import ultoi.task.Task;

import java.io.File;
import java.io.PrintWriter;

import java.nio.file.Path;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a Storage object that controls the read and write of file from and to hard disk.
 *
 * @author snoidetx
 * @version v0.0
 */
public class Storage {
    private final File file;

    /**
     * Creates a Storage object.
     *
     * @param path Path to the destination file.
     */
    public Storage(Path path) {
        this.file = path.toFile();
    }

    /**
     * Loads past tasks from hard disk.
     *
     * @return List of tasks.
     * @throws UltoiException If not loaded successfully.
     */
    public List<Task> load() throws UltoiException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                tasks.add(((AddCommand) Parser.parse(taskStr)).getTask());
            }
        } catch (Exception e) {
            throw UltoiException.failToLoadFileException();
        }

        return tasks;
    }

    /**
     * Saves tasks to hard disk.
     *
     * @param tasks Task list to be saved.
     * @throws UltoiException If not saved successfully.
     */
    public void save(TaskList tasks) throws UltoiException {
        PrintWriter pw;

        try {
            pw = new PrintWriter(this.file);
        } catch (Exception e) {
            throw UltoiException.failToSaveFileException();
        }

        pw.println(tasks.toInputString());

        return;
    }
}
