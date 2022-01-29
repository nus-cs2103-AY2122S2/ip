package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    /**
     * @inheritDoc
     */
    public String toFile() {
        return "T\t" + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
