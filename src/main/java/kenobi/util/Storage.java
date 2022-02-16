package kenobi.util;

import kenobi.task.Deadline;
import kenobi.task.Event;
import kenobi.task.Task;
import kenobi.task.TaskException;
import kenobi.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Storage class encapsulates the storage functionality of Kenobi.
 */
public class Storage {
    private boolean isDirty;
    private Path savePath;

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
    public TaskList load() {
        TaskList tasks = new TaskList();

        if (!Files.exists(savePath)) {
            return tasks;
        }

        try {
            Scanner fileScanner = new Scanner(savePath);
            while (fileScanner.hasNextLine()) {
                String[] taskString = fileScanner.nextLine().split(",.,");

                Task t;
                if (taskString[0].equals("T")) {
                    t = new ToDo(taskString[2]);
                } else if (taskString[0].equals("D")) {
                    t = new Deadline(taskString[2], LocalDate.parse(taskString[3]));
                } else { // "E"
                    t = new Event(taskString[2], LocalDate.parse(taskString[3]));
                }

                if (taskString[1].equals("1")) {
                    t.markAsDone();
                }

                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("WARNING: Kenobi could not load the save files");
        } catch (TaskException e) {
            System.out.println("WARNING: The save files may have been corrupted");
            tasks.clear();
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
