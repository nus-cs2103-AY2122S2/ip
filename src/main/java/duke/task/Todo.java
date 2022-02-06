package duke.task;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Creates a todo object using a given description.
     *
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Produces the representation of the todo in save file format.
     *
     * @return a string representation of the todo to be used in storage
     */
    @Override
    public String getAppendData() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Produces the todo task using data from the save file.
     *
     * @param data an array containing the data
     * @return a todo task containing the information from the data
     */
    public static Todo getTodoFromData(String[] data) {
        Todo todo = new Todo(data[2]);
        if (data[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Checks if a date is present in the todo.
     *
     * @return false
     */
    @Override
    public boolean isHasDate() {
        return false;
    }

    /**
     * Checks if a time is present in the todo.
     *
     * @return false
     */
    @Override
    public boolean isHasTime() {
        return false;
    }

    /**
     * Produces a string representation of the todo.
     *
     * @return a string representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
