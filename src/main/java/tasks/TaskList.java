package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructor for TaskList.
     * @param f The file to load the saved lists of tasks from.
     * @throws DukeException
     */
    public TaskList(File f) throws DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] strings = str.split(",");
                Task task = null;
                switch (strings[0]) {
                case "E" :
                    task = new Event(str, true);
                    tasks.add(task);
                    break;
                case "D" :
                    task = new Deadline(str, true);
                    tasks.add(task);
                    break;
                case "T" :
                    task = new Todo(str, true);
                    tasks.add(task);
                    break;
                default:
                    break;
                }
                if (str.charAt(5) == 'X') {
                    assert task != null;
                    task.setDone();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<Task>();
        }
    }

    /**
     * Constructor for TaskList when saved file has a loading error or does not exist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the user's current list of tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the current list of tasks.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }
}
