package paggro.task;

/**
 * This class encapsulates a task that the user needs to do.
 */
public class ToDo extends Task {
    /**
     * Default constructor of the ToDo object.
     * @param des String description of the ToDo object.
     */
    public ToDo(String des) {
        super(des);
    }

    /**
     * Constructor of the ToDo object specifying if the task is done.
     * @param des String description of the ToDo object.
     * @param isDone A boolean indicating if the task is complete.
     */
    public ToDo(String des, boolean isDone) {
        super(des, isDone);
    }

    /**
     * Parses the task into a string formatted to be saved to storage.
     * @return String to be saved to storage.
     */
    @Override
    public String parseTask() {
        return "T | " + Boolean.toString(isDone) + " | " + description;
    }

    /**
     * Returns a String representation of the ToDo object.
     * @return String representing the ToDo object.
     */
    @Override
    public String toString() {
        if (isDone) {
            return  "[T][X] " + description;
        } else {
            return "[T][ ] " + description;
        }
    }
}