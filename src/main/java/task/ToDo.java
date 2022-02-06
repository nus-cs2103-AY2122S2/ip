package task;

/**
 * Represents a type of Task - Todo.
 * A generic task that supports any type of text as a description.
 * Distinguishes itself from an Event or Deadline.
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description Description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns todo details.
     *
     * @return Output string to indicate todo's details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
