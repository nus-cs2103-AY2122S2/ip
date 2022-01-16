/**
 * Represents a to-do task. There is no deadline or timeslot associated.
 */
final class ToDo extends Task {

    /**
     * Initializes a to-do item, with a name.
     * @param description
     */
    protected ToDo(String description) {
        super(description);
    }

    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

}
