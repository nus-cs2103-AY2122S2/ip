package Duke;

public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description description of Todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a descipriton of Todo.
     *
     * @return description of ToDo with status icon.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}