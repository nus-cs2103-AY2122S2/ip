package duke;

import duke.exception.LoadException;
import duke.exception.RonException;
import duke.exception.WriteException;
import duke.parser.StorageParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final List<Task> store;
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
                }
                if (tokens[0].charAt(0) == "D".charAt(0)) {
                    Task task = new Deadline("deadline " + tokens[2] + "/by " + tokens[3]);
                    if (Integer.parseInt(tokens[1].trim()) == 1) {
                        task.toggleStatus();
                    }
                    store.add(task);
                }
                if (tokens[0].charAt(0) == "E".charAt(0)) {
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

    public Task get(int index) {
        return this.store.get(index);
    }

    public void printTasks() {
        int i = 1;
        for (Task task : this.store) {
            System.out.print(i + ".");
            System.out.println(task);
            i++;
        }
    }

    public int size() {
        return this.store.size();
    }

    public void add(Task task) {
        this.store.add(task);
    }

    public Task remove(int index) {
        return this.store.remove(index);
    }

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
