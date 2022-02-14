package duke;

/**
 * Class that represents todo related tasks.
 */
public class Todo extends Task {
    protected Tag tag;
    /**
     * Constructs a Todo object.
     *
     * @param description description of Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object.
     *
     * @param description description of Todo object.
     * @param tag tag for todo object.
     */
    public Todo(String description, Tag tag) {
        super(description);
        this.tag = tag;
    }

    @Override
    public Todo tag(Tag taskTag) {
        return new Todo(description, taskTag);
    }

    /**
     * Returns a string of Todo object to save to disk.
     *
     * @return String of obejct to save to disk.
     */
    @Override
    public String toStringForSave() {
        return tag == null
                ? "T " + super.toStringForSave()
                : "T " + super.toStringForSave() + " " + this.tag.toString();
    }

    /**
     * Returns a String representation of the Todo object.
     *
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return tag == null
                ? ("[T]" + super.toString() + "\n")
                : ("[T]" + super.toString() + " " + this.tag.toString() + "\n");
    }
}
