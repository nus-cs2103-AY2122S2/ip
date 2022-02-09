package baron.tasks;

/**
 * Represents a to-do task without any additional characteristic. Inherited from {@code Task}.
 *
 * @see Task
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} object with the specified description.
     *
     * @param description the description of this task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks if this {@code ToDo} object is equal to the specified object.
     *
     * @param o an {@code Object} to be compared with.
     * @return true if the specified object equals to this {@code ToDo} object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof ToDo)) {
            return false;
        }

        ToDo toDo = (ToDo) o;

        return this.description.equals(toDo.description);
    }

    /**
     * Returns the string representation of this {@code ToDo} task.
     *
     * @return the string representation of this {@code ToDo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the encoded string of this {@code ToDo} task to be stored in the storage
     * using the specified delimiter.
     *
     * @param delimiter the string to be inserted in between various information of this task.
     * @return the encoded string of this {@code ToDo} task to be stored in the storage
     *         using the specified delimiter.
     */
    @Override
    public String toSaveString(String delimiter) {
        return "T" + delimiter + super.toSaveString(delimiter);
    }
}
