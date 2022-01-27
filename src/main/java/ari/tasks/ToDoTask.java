package main.java.ari.tasks;

/**
 * ToDoTask extends Task
 */
public class ToDoTask extends Task {
    /**
     * Constructor of ToDoTask
     *
     * @param description description of ToDoTask
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns string representation of ToDoTask
     *
     * @return string representation of ToDoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of ToDoTask in save file
     *
     * @return string representation of TaDoTask in save file
     */
    @Override
    public String writeToFile() {
        return "todo " + super.writeToFile();
    }
}
