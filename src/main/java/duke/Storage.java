package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents Duke's storage for tasks.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads saved tasks to the task list.
     * @returns A list of tasks.
     * @throws DukeException when tasks file or directory is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String fullCommand = sc.nextLine();
                String[] commandArr = fullCommand.split(" \\| ");
                String type = commandArr[0];
                boolean status = (Integer.parseInt(commandArr[1]) == 1);
                String description = commandArr[2];
                switch (type) {
                case "E":
                    String at = commandArr[3];
                    tasks.add(new Event(description, at, status));
                    break;
                case "D":
                    String by = commandArr[3];
                    tasks.add(new Deadline(description, by, status));
                    break;
                case "T":
                    tasks.add(new ToDo(description, status));
                    break;
                default:
                    break;
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to storage.
     * @param tasks A list of tasks.
     * @throws DukeException when tasks cannot be saved.
     */
    public void save(TaskList tasks) throws DukeException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).save());
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
