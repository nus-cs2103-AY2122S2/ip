package Task;

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
     */
    public void mark() {
        if (!isDone) {
            isDone = true;
            System.out.println("Nice! I've marked this task as done: \n"
                    + toString());
        } else {
            System.out.println("This task is already done! \n"
                    + toString());
        }
    }

    /**
     * Unmarks a Task as completed.
     */
    public void unmark() {
        if (isDone) {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet: \n"
                    + toString());
        } else {
            System.out.println("This task has not been completed! \n"
                    + toString());
        }
    }

    /**
     * Removes a Task from list of Tasks.
     */
    public ArrayList<Task> delete(ArrayList<Task> list, int index) {
        list.remove(index);
        System.out.println("Noted. I've removed this task: \n"
                + toString());
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
