package yalebot;

import yalebot.Task;

/**
 * Subclass of Task,
 * basic implementation of Task object
 */
public class ToDo extends Task {
    /**
     * Constructor method
     * @param name
     * @param isMarked
     */
    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    /**
     * Returns a customised String
     * @return Customised String format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
