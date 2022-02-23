package sparrow.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sparrow.commons.exceptions.SparrowException;
import sparrow.logic.task.Deadline;
import sparrow.logic.task.Event;
import sparrow.logic.task.Task;
import sparrow.logic.task.ToDo;
import sparrow.model.Priority;
import sparrow.model.Status;
import sparrow.model.TaskList;


/**
 * Represents Duke's storage for tasks.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads saved tasks to the task list.
     *
     * @return A list of tasks.
     * @throws SparrowException when tasks file or directory is not found.
     */
    public ArrayList<Task> load() throws SparrowException {
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
                Status status = parseStatus(commandArr[1]);
                String description = commandArr[2];
                Priority priority = parsePriority(commandArr[commandArr.length - 1]);
                switch (type) {
                case "E":
                    String at = commandArr[3];
                    tasks.add(new Event(description, status, priority, at));
                    break;
                case "D":
                    String by = commandArr[3];
                    tasks.add(new Deadline(description, status, priority, by));
                    break;
                case "T":
                    tasks.add(new ToDo(description, status, priority));
                    break;
                default:
                    break;
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new SparrowException(e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to storage.
     *
     * @param tasks A list of tasks.
     * @throws SparrowException when tasks cannot be saved.
     */
    public void save(TaskList tasks) throws SparrowException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).save());
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new SparrowException(e.getMessage());
        }
    }

    /**
     * Parses a string to a task status.
     * @param s The task status as a string.
     */
    public Status parseStatus(String s) {
        int i = Integer.parseInt(s);
        if (i == 0) {
            return Status.NOT_DONE;
        }
        return Status.DONE;
    }

    /**
     * Parses a string to a task priority.
     * @param s The task priority as a string.
     */
    public Priority parsePriority(String s) {
        int i = Integer.parseInt(s);
        switch(i) {
        case 0:
            return Priority.LOW;
        case 2:
            return Priority.HIGH;
        default:
            return Priority.MEDIUM;
        }
    }
}
