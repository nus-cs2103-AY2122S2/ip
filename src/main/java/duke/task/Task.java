package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * This is a Task.
 */
public class Task {

    public static final DateTimeFormatter DATE_TIME_FORMAT_IN = DateTimeFormatter.ofPattern("d/M/y kkmm");
    public static final DateTimeFormatter DATE_TIME_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM d y");
    protected boolean isMarked = false;

    protected String name;
    /***
     * Constructs a Task from the given name.
     * @param name given name of the task.
     */
    Task(String name) {
        this.name = name;
    }
    /***
     * Void methods that sets the completeness of the task based on the input.
     * @param isMarked representing the completeness of the task.
     */
    public void setMark(boolean isMarked) {
        this.isMarked = isMarked;
    }
    /**
     * Void methods that inform the user when the state of completeness has been
     * toggled.
     * @param val representing the completeness of the task.
     */
    public void markResponse(boolean val) {
        System.out.println(
            "____________________________________________________________");
        if (val) {
            System.out.println("Nice! I've marked this task as done:\n");
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n");
        }
        System.out.println(this.display());
        System.out.println(
            "____________________________________________________________");
    }

    /**
     * returns a string based on if the task is marked or not.
     * @return a string on the status task.
     */
    public String markDisplay() {
        if (this.isMarked) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * returns a string based on if the task is marked or not.
     * @return a string on the status task.
     */
    public String markStore() {
        if (this.isMarked) {
            return "1";
        } else {
            return "0";
        }
    }

    public String toStore() {
        return "";
    }
    /**
     * method that returns the string representation of the Task
     * @return a String representation fo the task.
     */
    public String display() {
        return "[" + markDisplay() + "] " + this.name;
    }
    public boolean isRelated(String term) {
        return name.contains(term);
    }
}
