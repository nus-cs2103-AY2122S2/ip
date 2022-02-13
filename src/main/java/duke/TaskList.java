package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import duke.exception.LoadException;
import duke.exception.RonException;
import duke.exception.WriteException;
import duke.parser.StorageParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Stores tasks from the chatbot in an ArrayList for easy retrieval
 */
public class TaskList {
    private List<Task> store;
    private final File file;

    public TaskList(File file) throws RonException {
        this.file = file;
        this.store = new ArrayList<>();
        try {
            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                String currTask = fr.nextLine();
                String[] tokens = currTask.split("\\| ");
                if (tokens[0].charAt(0) == "T".charAt(0)) {
                    Task task = new Todo("todo " + tokens[2]);
                    if (Integer.parseInt(tokens[1].trim()) == 1) {
                        task.toggleStatus();
                    }
                    store.add(task);
                } else if (tokens[0].charAt(0) == "D".charAt(0)) {
                    Task task = new Deadline("deadline " + tokens[2] + "/by " + tokens[3]);
                    if (Integer.parseInt(tokens[1].trim()) == 1) {
                        task.toggleStatus();
                    }
                    store.add(task);
                } else if (tokens[0].charAt(0) == "E".charAt(0)) {
                    Task task = new Event("event " + tokens[2] + "/at " + tokens[3]);
                    if (Integer.parseInt(tokens[1].trim()) == 1) {
                        task.toggleStatus();
                    }
                    store.add(task);
                }
            }
            fr.close();
        } catch (IOException e) {
            throw new LoadException();
        }
    }

    public TaskList(String filePath) {
        this.file = new File(filePath);
        this.store = new ArrayList<>();

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Retrieves task from given index
     *
     * @param index
     * @return Task in given index
     */
    public Task get(int index) {
        return this.store.get(index);
    }

    public String printTasks() {
        AtomicInteger count = new AtomicInteger(0);
        String returnString = this.store.stream()
                .map(task -> count.incrementAndGet() + "." + task.toString() + "\n")
                .reduce("", (x, y) -> x + y);
        return returnString;
    }

    public String findTasks(String substring) {
        List<Task> tempList = new ArrayList<>(this.store);
        AtomicInteger count = new AtomicInteger(0);
        String returnString = tempList.stream().filter(task -> task.getDescription().contains(substring))
                .map(task -> count.incrementAndGet() + "." + task.toString() + "\n")
                .reduce("", (x, y) -> x + y);

        return returnString;
    }

    public boolean isDuplicate(Task task) {
        for (Task currTask : this.store) {
            if (task.equals(currTask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get number of tasks in current tasklist
     *
     * @return int value of size of tasklist
     */
    public int size() {
        return this.store.size();
    }

    /**
     * Adds param task into tasklist
     *
     * @param task
     */
    public void add(Task task) {
        this.store.add(task);
    }

    /**
     * Removes task from param index from tasklist
     *
     * @param index
     * @return task removed from tasklist
     */
    public Task remove(int index) {
        return this.store.remove(index);
    }

    /**
     * Writes current state snapshot of tasklist into .txt file
     * WriteException thrown if backup fails
     *
     * @throws WriteException
     */
    public void backup() throws WriteException {
        try {
            FileWriter fw = new FileWriter(this.file, false);
            for (Task task : store) {
                fw.write(new StorageParser(task).toString());
            }
            fw.close();
        } catch (IOException e) {
            throw new WriteException();
        }
    }
}
