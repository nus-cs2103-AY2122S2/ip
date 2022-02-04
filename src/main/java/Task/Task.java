package task;

import java.util.ArrayList;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for Task object.
     *
     * @param description description of Task to be added
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status of Task.
     *
     * @return a "X" if Task is done and " " if otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a Task as completed.
     *
     * @return String message to print on GUI
     */
    public String mark() {
        if (!isDone) {
            isDone = true;
            return "Nice! I've marked this task as done: \n"
                    + toString();
        } else {
            return "This task is already done! \n"
                    + toString();
        }
    }

    /**
     * Unmarks a Task as completed.
     *
     * @return String message to print on GUI
     */
    public String unmark() {
        if (isDone) {
            isDone = false;
            return "OK, I've marked this task as not done yet: \n"
                    + toString();
        } else {
            return "This task has not been completed! \n"
                    + toString();
        }
    }

    /**
     * Removes a Task from a list at given index.
     *
     * @param list list of Tasks
     * @param index index of the Task to be deleted
     * @return list of Tasks without deleted task
     */
    public ArrayList<Task> delete(ArrayList<Task> list, int index) {
        list.remove(index);
        return list;
    }

    /**
     * Sets a Task as isDone.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Prints Task details as a String.
     *
     * @return String form of Task details
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
