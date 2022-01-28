package jose;

import java.util.ArrayList;
import jose.task.*;

/**
 * Class that represents a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The default constructor that sets up an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * A constructor that sets up the tasks list based on the given tasks.
     *
     * @param arr An ArrayList containing tasks.
     */
    public TaskList(ArrayList<String> arr) {
        tasks = new ArrayList<>();
        String[] task;
        boolean isDone;

        for (String s : arr) {
            task = s.split("\\|");
            isDone = task[1].equals("1");
            if (task[0].equals("T")) {
                tasks.add(new ToDo(task[2], isDone));
            } else if (task[0].equals("D")) {
                tasks.add(new Deadline(task[2], isDone, task[3]));
            } else {
                tasks.add(new Event(task[2], isDone, task[3]));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
