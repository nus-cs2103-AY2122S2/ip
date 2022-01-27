package duke;

/**
 Class to represent the todo task
 Supports description of task
 */
public class Todo extends Task {

    char type;

    public Todo(String description) {
        super(description);
        this.type = 't';
    }

    /**
     *
     * Method to convert task to String type to be printed in the task list
     *
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
