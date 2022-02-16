package batman.tasks;

import java.time.LocalDateTime;

public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;

    /**
     * Encapsulates tasks to be done, that can be marked as completed.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon of task's status.
     * If task is done, X is returned.
     *
     * @return String of icon of task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Prints total number of tasks in existing list.
     *
     * @param totalTasks Total number of tasks.
     * @return String object of total number of tasks.
     */
    public String printNoOfTasks(int totalTasks) {
        return "Now you have " + totalTasks + " tasks in the list.";
    }

    public abstract boolean contains(String keyword);
    public abstract String taskType();
    public abstract String appendToFile();
    public abstract LocalDateTime getDateTime();

    /**
     * Sorts the tasks chronologically.
     *
     * @param o1 Task object to be compared with.
     * @return An integer, returns 0 if equal,
     *          less than 0 if smaller,
     *          greater than 0 if greater.
     */
    @Override
    public int compareTo(Task o1) {
        return getDateTime().compareTo(o1.getDateTime());
    }

    /**
     * Returns status icon and description of tasks.
     *
     * @return String object of task's details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
