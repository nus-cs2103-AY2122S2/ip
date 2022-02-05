package duke;

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
    public void list() {
        Ui.list();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i +". " + get(i - 1));
        }
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param task task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
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
    public void update(int id, String command) {
        if (id > tasks.size()) {
            return;
        }
        switch (command) {
            case "mark" :
                if (tasks.get(id - 1).isDone()) {
                    Ui.alreadyDone(tasks.get(id - 1));
                } else {
                    tasks.get(id - 1).markAsDone();
                    Ui.done(tasks.get(id - 1).toString());
                }
                break;
            case "unmark" :
                if (!tasks.get(id - 1).isDone()) {
                    Ui.alreadyNotDone(tasks.get(id - 1));
                } else {
                    tasks.get(id - 1).markAsNotDone();
                    Ui.notDone(tasks.get(id - 1).toString());
                }
                break;
            case "remove" :
                Ui.remove(tasks.remove(id - 1).toString());
                break;
            default :
                Ui.unknownCommand(command);
                break;
        }
    }
}
