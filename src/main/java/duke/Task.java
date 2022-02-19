package duke;

import java.time.LocalDate;

/**
 * Task class to save description of tasks and mark them as done or undone.
**/
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Constructor of Task.
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of Task that need to store dates.
     * 
     * @param description
     * @param date
     */
    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Returns the status of a task in string format.
     * @return status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    
    /**
     * Marks task as done and notifies user of the changes.
     * @return {@code String} updated state of task.
     */
    public String markAsDone() {
        isDone = true;
        String message = "Nice! I've marked this task as done:\n" + this.getStatusIcon() + " " + this.description;
        return message;
    }

    /**
     * Marks task as undone and notifies user of the changes.
     * @return {@code String} updated state of task.
     */
    public String markAsUndone(){
        isDone = false;
        String message = "Ok! I've marked this task as not done yet:\n" + this.getStatusIcon() + " "
        + this.description;
        return message;
    }
}
