package pyke.task;


public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the to-do class to a style used in local files for saving.
     *
     * @return the formatted string for saving.
     */
    @Override
    public String toSavedFile() {
        return "T | " + super.toSavedFile();
    }

    /**
     * Formats the to-do class to a style used for output.
     *
     * @return the formatted string for output.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
