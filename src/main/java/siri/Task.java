package siri;

/**
 * Represents a task object
 */
public class Task {
    String description;
    String initialLetter;
    boolean isDone;

    /**
     * Returns new Task
     * @param description Description of task.
     * @param initialLetter Initial letter of the task, e.g T, D or E.
     */
    public Task(String description, String initialLetter) { //task constructor
        this.description = description;
        this.initialLetter = initialLetter;
        this.isDone = false; //initialise as false
    }

    /**
     * Returns a string "X" if task has been marked or " " if not.
     * @return "X" or " ".
     */
    public String getStatusIcon() { //will return either "X" or " " depending if task is done or not
        return isDone
                ? "X" //if true mark with X
                : " "; //if false do not mark, leave as empty space
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string method of a Task
     * @return String method of a Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
