package tasks;

import exceptions.DukeException;
import storage.Storage;
import user.Ui;

import java.util.ArrayList;

/** A class that encapsulates the lists of tasks to be completed. */
public class Tasklist {

    public Storage storage = new Storage();
    public ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Loads the tasks from storage, if exists.
     */
    public void loadTasks() {
        try {
            allTasks = storage.loadTasks();
        } catch (DukeException err) {
            Ui.prettyPrint(err.toString());
        }
    }

    /**
     * Saves the tasks to storage.
     *
     * @throws DukeException if the file was tampered with during the execution of the program.
     */
    public void saveTasks() throws DukeException {
        storage.saveTasks(allTasks);
    }

    /**
     * Returns the number of tasks inputted by the user.
     *
     * @return An integer, representing the number of tasks.
     */
    public int getNumTasks() {
        return this.allTasks.size();
    }

    /**
     * Returns the specific task stored.
     *
     * @param taskNum The task number.
     * @return The appropriate task.
     */
    public Task getTask(int taskNum) {
        return this.allTasks.get(taskNum);
    }

    /**
     * Adds a task.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.allTasks.add(t);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNum The number of the task to be marked as completed.
     * @return An array of strings, containing the success message if task is successfully marked.
     * @throws DukeException If the task to be marked as completed is already completed.
     */
    public String[] markTask(int taskNum) throws DukeException {
        Task thisTask = allTasks.get(taskNum);
        if (thisTask.isDone()) {
            throw new DukeException("This task is already done!");
        } else {
            thisTask.setDone(true);
            return new String[] {"Nice! I've marked this task as done:", thisTask.toString()};
        }
    }

    /**
     * Marks a task as not completed.
     *
     * @param taskNum The number of the task to be marked as not completed.
     * @return An array of strings, containing the success message if task is successfully marked.
     * @throws DukeException If the task to be marked as not completed was not completed yet.
     */
    public String[] unmarkTask(int taskNum) throws DukeException {
        Task thisTask = allTasks.get(taskNum);
        if (!thisTask.isDone()) {
            throw new DukeException("This task has not been done yet!");
        } else {
            thisTask.setDone(false);
            return new String[] {"Ok, I've marked this task as not done yet:", thisTask.toString()};
        }
    }

    /**
     * Deletes a task from the lists of tasks.
     *
     * @param taskNum The number of the task to be deleted.
     * @return An array of strings, containing the success message if task is successfully deleted.
     */
    public String[] deleteTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        allTasks.remove(taskNum);
        return new String[] {
            "Noted. I've removed this task:",
            thisTask.toString(),
            String.format("Now you have %d tasks in the list.", allTasks.size())};
    }

    public ArrayList<Task> findTasks(String keywords) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : allTasks) {
            if (t.getDescription().contains(keywords)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}