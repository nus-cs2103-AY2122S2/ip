package duke.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage that reads and writes to a local file.
 *
 * @author Peter
 */
public class Storage {
    /**
     * File that is to be operated on.
     */
    private final File file;

    private enum TaskType {
        T, E, D
    }

    /**
     * Constructor for a storage unit.
     *
     * @param filePath Path at which the local file is to be stored.
     * @throws DukeException If file path is invalid.
     */
    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);

        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }

    /**
     * Clears the local file associated with the storage.
     *
     * @throws DukeException If write to local file is unsuccessful.
     */
    public void clearFile() throws DukeException {
        this.writeToFile(new TaskList());
    }

    /**
     * Writes to the local file associated with the storage.
     *
     * @param taskList List of tasks that is to be written to local file.
     * @throws DukeException If write to local file is unsuccessful.
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(taskList.toData());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }

    /**
     * Reads from the local file associated with the storage.
     *
     * @return List of tasks read from local file.
     * @throws DukeException If read from local file is unsuccessful.
     */
    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] breakdown = line.split(" \\| ");
                String command = breakdown[0];

                try {
                    TaskType taskType = TaskType.valueOf(command);

                    switch (taskType) {
                    case T:
                        tasks.add(new Todo(breakdown[2],
                                Boolean.parseBoolean(breakdown[1])));
                        break;
                    case E:
                        tasks.add(new Event(breakdown[2],
                                Boolean.parseBoolean(breakdown[1]),
                                LocalDateTime.parse(breakdown[3])));
                        break;
                    case D:
                        tasks.add(new Deadline(breakdown[2],
                                Boolean.parseBoolean(breakdown[1]),
                                LocalDateTime.parse(breakdown[3])));
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    throw new DukeException("INVALID TASK TYPE");
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }
}
