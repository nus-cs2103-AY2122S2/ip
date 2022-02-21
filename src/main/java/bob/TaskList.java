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
     * @param tasks List of Tasks to create a TaskList from.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public String add(Task task) {
        tasks.add(task);
        return Ui.newTask(task, tasks.size());
    }

    /**
     * Returns the Task at position specified by idx.
     *
     * @param idx Position of Task to be returned
     * @return Task to be returned.
     */
    public Task get(int idx) {
        assert 0 <= idx && idx < tasks.size();
        return tasks.get(idx);
    }

    /**
     * Returns the Tasks in the TaskList.
     */
    public String list() {
        String str = Ui.list();
        int count = 1;
        for (Task entry : tasks) {
            str += "    " + count++ + "." + entry + "\n";
        }
        return str + Ui.printLine();
    }

    /**
     * Updates the TaskList according to the given command.
     *
     * @param command Command to indicate action.
     * @param idx     Index of task to be acted upon.
     */
    public String update(String command, int idx) {
        assert 0 <= idx && idx < tasks.size();
        switch (command) {
        case "mark":
            tasks.get(idx - 1).toggleDone();
            return Ui.mark(tasks.get(idx - 1));

        case "unmark":
            tasks.get(idx - 1).toggleNotDone();
            return Ui.unmark(tasks.get(idx - 1));

        case "delete":
            Task removedTask = tasks.remove(idx - 1);
            String str = Ui.delete(tasks.size(), removedTask);
            return str;

        default:
            return "Invalid command!";
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
    public String find(String keyword) {
        String str = Ui.find();
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                str += "    " + count++ + "." + task + "\n";
            }
        }
        return str + Ui.printLine();
    }

    /**
     * Returns a string describing the status of the archive command.
     *
     * @return A string describing status of archive command.
     */
    public void archive() {
        String folderPath = "./data";
        String filePath = "./data/bob_archive.txt";
        Storage archiveStorage = Storage.createStorage(folderPath, filePath);
        archiveStorage.writeTaskListToFile(this);
        tasks.clear();
    }
}
