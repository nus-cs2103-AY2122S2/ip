package juke;

/**
 * Represents the tasks that the user inputs into the program, which is defined by
 * description and completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructor for all possible tasks, including todo, deadline and event
     * @param description The description of the task given by user
     * @return
     * @throws
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Checks if task is Mark as done or not and gives the output
     *
     * @param
     * @return "X" or " " depending on whether isDone is true or false respectively
     * @throws
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return "";
    }
}
