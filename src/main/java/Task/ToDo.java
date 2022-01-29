package Task;

import Task.Task;

/**
 * a type of task with no date
 */
public class ToDo extends Task {

    /**
     * Create a new ToDo class
     *
     * @param description string of the description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    @Override
    public String saveString() {
        return "T" + "|" + (this.isDone ? "1" : "0") + "|" + this.description;
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}