package duke.modules;

/**
 * Represents a Task which has a name and no other attributes.
 */
class ToDo extends Task {

    /**
     * Initialise a ToDo object with a name.
     *
     * @param name The name given to the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[T][X] " + super.getName();
        } else {
            return "[T][ ] " + super.getName();
        }
    }
}
