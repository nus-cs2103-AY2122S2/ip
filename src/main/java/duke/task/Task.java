package duke.task;

import duke.exception.DukeException;

import java.util.Locale;

/**
 * Task class
 */
public class Task {

    private String description;
    private boolean isDone;
    private Priority priorityLevel;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        EMPTY
    };

    /**
     * Constructor for task object
     * @param description description of task
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        assert !this.isDone : "isDone should be false after instantiating the object";
        try {
            this.priorityLevel = Priority.valueOf(determinePriority(priority));
        } catch (DukeException e) {
            System.out.print(e.getMessage() + "\n");
        }

    }

    public String determinePriority(String priority) throws DukeException{
        String upperCasePriority = priority.toUpperCase();
        if (upperCasePriority.equals("LOW") || upperCasePriority.equals("L")) {
            return "LOW";
        } else if (upperCasePriority.equals("MEDIUM") || upperCasePriority.equals("M")) {
            return "MEDIUM";
        } else if (upperCasePriority.equals("HIGH") || upperCasePriority.equals("H")) {
            return "HIGH";
        } else if (priority.equals(" ") || priority.equals("")){
            return "EMPTY";
        } else {
            throw new DukeException("Incorrect priority level format!");
        }
    }

    /**
     * Getter for checking task
     * @return boolean for whether task is done
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Setter for to mark task as done
     * @param bool Set task according to boolean
     */
    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    public String getPriorityLevel(Priority priority) {
        switch (priorityLevel) {
        case LOW:
            return "[L] ";
        case MEDIUM:
            return "[M] ";
        case HIGH:
            return "[H] ";
        default:
            return "[ ] ";
        }
    }

    /**
     * Override the toString method from the object class
     * @return the task description with checkbox depending on isDone
     */
    @Override
    public String toString() {
        return "["
                + (this.isDone ? "X" : " ")
                + "]"
                + getPriorityLevel(priorityLevel)
                + this.description;
    }
}
