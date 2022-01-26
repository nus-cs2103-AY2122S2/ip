package duke;

public class Todo extends Task {
    /**
     * Constructs a Todo object.
     *
     * @param description description of Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string of Todo object to save to disk.
     *
     * @return String of obejct to save to disk.
     */
    @Override
    public String toStringForSave() {
        return "T "+ super.toStringForSave();
    }

    /**
     * Returns a String representation of the Todo object.
     *
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
