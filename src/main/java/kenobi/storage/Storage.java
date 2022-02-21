package kenobi.storage;

import kenobi.parser.Parser;
import kenobi.task.Task;
import kenobi.util.TaskList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * The Storage class encapsulates the storage functionality of Kenobi.
 */
public class Storage {
    private boolean isDirty;
    private Path savePath;
    private String loadMsg;

    /**
     * Constructs a new Storage with the specified save path.
     *
     * @param savePath The path that directs to the storage that Kenobi uses.
     */
    public Storage(String savePath) {
        this.savePath = Path.of(savePath);
        isDirty = false;
    }

    /**
     * Loads existing tasks in savePath if exists.
     *
     * @return a TaskList containing the tasks from savePath or an empty TaskList if savePath doesn't exist.
     */
    public TaskList load() throws LoadStorageException {
        TaskList tasks = new TaskList();

        if (!Files.exists(savePath)) {
            return tasks;
        }

        try {
            Scanner fileScanner = new Scanner(savePath);
            while (fileScanner.hasNextLine()) {
                Task nextTask = Parser.parseTask(fileScanner.nextLine());

                tasks.add(nextTask);
            }
        } catch (Exception e) {
            throw new LoadStorageException(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given TaskList to savePath.
     *
     * @param tasks The TaskList to be saved.
     */
    public void save(TaskList tasks) {
        try {
            Files.createDirectories(savePath.getParent());

            BufferedWriter out = Files.newBufferedWriter(savePath);
            for (Task task : tasks) {
                out.append(task.toSave());
            }
            out.close();
        } catch (IOException ioException) {
            System.out.println("Kenobi could not save the tasks");
        }
    }
}
