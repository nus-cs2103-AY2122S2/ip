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
     * Constructor of Tasks that need to store dates.
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
     * 
     * @return status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public static String markPrint;
    
    /**
     * Marks task as done and notifies user of the changes.
     */
    public void markAsDone() {
        isDone = true;
        markPrint = "Nice! I've marked this task as done:\n" + this.getStatusIcon() + " " + this.description;
        System.out.println(markPrint);
    }

    /**
     * Marks task as undone and notifies user of the changes.
     */
    public static String unmarkPrint;
    //mark a task as undone and notifies the user
    public void markAsUndone(){
        isDone = false;
        unmarkPrint = "Ok! I've marked this task as not done yet:\n" + this.getStatusIcon() + " "
        + this.description;
        System.out.println(unmarkPrint);
    }
}
