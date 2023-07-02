package tasks;

import tasks.Task;

/**
 * Class for todo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for the class.
     * @param description Description of the class.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to return the String for the class.
     * @return String for the class
     */
    @Override
     public String toString() {
         return String.format("[T] %s", super.toString());
     }
    }
