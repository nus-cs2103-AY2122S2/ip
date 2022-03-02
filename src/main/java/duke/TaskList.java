package duke;

import java.util.ArrayList;

/**
 * TaskList class handles all the operations to do with creating,
 * updating  and displaying the list of tasks
 */
public class TaskList {
    /**
     * Stores each individual Task object
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks the tasks to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task.
     *
     * @param task the task to be added
     */
    void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the no of tasks currently in the list.
     *
     * @return the length of the task list
     */
    int size() {
        return tasks.size();
    }

    /**
     * Returns the no of tasks currently in the list.
     *
     * @return the length of the task list
     */
    Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at that specific index in the list.
     *
     * @param index the position of the task to be deleted.
     * @return the Task that was deleted.
     */
    Task remove(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    /**
     * Prints the task list.
     *
     */
    String printList() {
        if (tasks.size() == 0) {
            return ("Congrats there's nothing on your list!");
        } else {
            String out = ("Here's everything on your list rn:\n");
            for (Integer i = 1; i <= tasks.size(); i++) {
                out += (i.toString() + " " + tasks.get(i - 1) + "\n");
            }
            return out;
        }
    }

    /**
     * Searches the task list for tasks containing
     * the specified string.
     *
     * @param searchString The string to search for
     */
    String search(String searchString) {
        ArrayList<Integer> results = new ArrayList<>();
        String out = "";
        for (Task task : tasks) {
            if (task.toString().contains(searchString)) {
                results.add(tasks.indexOf(task));
            }
        }
        if (results.size() > 0) {
            out += ("Here's everything that matches your search: \n");
            for (int i = 0; i < results.size(); i++) {
                out += (results.get(i) + " " + tasks.get(results.get(i)) + "\n");
            }
        } else {
            out += ("Nothing in the list matches your search :)\n");
            System.out.println("Hmm nothing in the list matches your search :)");
        }
        return out;
    }
}
