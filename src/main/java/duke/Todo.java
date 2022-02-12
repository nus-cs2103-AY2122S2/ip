package duke;

/**
 * Todo task
 */
public class Todo extends Task{
    public boolean isDone;

    /**
     * Constructor for a todo task
     * @param description description of todo
     */
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Constructor with ability to indicate whether todo has
     * been done or not
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Outputs the todo in a format that can be saved
     * @return todo as a string to be saved
     */
    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "T | " + isDoneNumber + " | " + description +
                System.lineSeparator();
    }

    /**
     * Returns todo as a string
     * @return todo as a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
