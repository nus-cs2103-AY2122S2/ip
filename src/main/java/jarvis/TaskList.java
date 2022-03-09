package jarvis;

import java.util.LinkedList;

/**
 * Represents a list of tasks.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class TaskList {
    protected LinkedList<Task> tasks;

    /**
     * Returns an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Returns a TaskList object which stores the LinkedList of tasks.
     *
     * @param tasks LinkedList holding all of the tasks
     * @exception DukeException any error when loading the list of tasks
     */
    public TaskList(LinkedList<Task> tasks) throws DukeException {
        this.tasks = tasks;
    }

    /**
     * Lists out all of the tasks stored in the list.
     */
    public String list() {
        String output = Ui.list();
        for (int i = 1; i <= tasks.size(); i++) {
            output += "\n" + i + ". " + get(i - 1);
        }
        return output;
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param task task to be added to the list
     */
    public String add(Task task) {
        tasks.add(task);
        return Ui.add(task);
    }

    /**
     * Returns the task at the specified id.
     *
     * @param id id of the task
     * @return task with this id
     */
    public Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Updates the task at the specified id with respects to the command provided.
     *
     * @param id id of the task
     * @param command how the task should be updated (mark done, mark not done or removed)
     */
    public String update(int id, String command) {
        if (id > tasks.size()) {
            return Ui.invalidId();
        }
        assert id <= tasks.size() : "id should be in the range of the task list's size";
        switch (command) {
        case "mark":
            if (tasks.get(id - 1).isDone()) {
                return Ui.alreadyDone(tasks.get(id - 1));
            } else {
                tasks.get(id - 1).setDone();
                return Ui.done(tasks.get(id - 1).toString());
            }
        case "unmark":
            if (!tasks.get(id - 1).isDone()) {
                return Ui.alreadyNotDone(tasks.get(id - 1));
            } else {
                tasks.get(id - 1).setNotDone();
                return Ui.notDone(tasks.get(id - 1).toString());
            }
        case "remove":
            return Ui.remove(tasks.remove(id - 1).toString());
        default:
            return Ui.unknownCommand(command);
        }
    }

    /**
     * Displays all tasks in the todo list which consists the specified keyword.
     *
     * @param keyword keyword to search for tasks
     */
    public String find(String keyword) {
        String output = Ui.find();
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                output += "\n" + counter++ + ". " + get(i);
            }
        }
        return output;
    }

    /**
     * Checks if this Task is already contained in the list.
     *
     * @param task Task to check for its existence
     * @return boolean whether or not the Task is in the list
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }
}
