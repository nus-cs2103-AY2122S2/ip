package ultoi.util;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

import ultoi.command.AddCommand;
import ultoi.command.MarkCommand;

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
    public TaskList load() throws UltoiException {
        TaskList tasks = new TaskList();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                if (taskStr.startsWith("mark") || taskStr.startsWith("unmark")) {
                    new MarkCommand(taskStr).execute(tasks);
                } else {
                    tasks.addTask(((AddCommand) Parser.parse(taskStr)).getTask());
                }
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

        System.out.println(tasks.toInputString());

        pw.close();

        return;
    }
}
