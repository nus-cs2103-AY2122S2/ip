package bob;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class around a List of Tasks, providing access and modification methods.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with the provided List of Tasks.
     *
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        Ui.newTask(task, tasks.size());
    }

    /**
     * Returns the Task at position specified by idx.
     *
     * @param idx Position of Task to be returned
     * @return Task to be returned.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Prints the Tasks in the TaskList.
     */
    public void list() {
        Ui.list();
        int count = 1;
        for (Task entry : tasks) {
            System.out.println("    " + count++ + "." + entry);
        }
        Ui.printLine();
    }

    /**
     * Updates the TaskList according to the given command.
     *
     * @param cmd Command to indicate action.
     * @param idx Index of task to be acted upon.
     */
    public void update(String cmd, int idx) {
        switch (cmd) {
        case "mark":
            tasks.get(idx - 1).toggleDone();
            Ui.mark(tasks.get(idx - 1));
            break;

        case "unmark":
            tasks.get(idx - 1).toggleNotDone();
            Ui.unmark(tasks.get(idx - 1));
            break;

        case "delete":
            Ui.delete(tasks.size(), tasks.get(idx - 1));
            tasks.remove(idx - 1);
            break;

        default:
            System.out.println("Invalid command!");
        }
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Lists all Tasks in the TaskList that contains a keyword.
     *
     * @param keyword String used to find matching Tasks.
     */
    public void find(String keyword) {
        Ui.find();
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println("    " + count++ + "." + task);
            }
        }
        Ui.printLine();
    }
}
