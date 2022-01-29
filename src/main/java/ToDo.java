import java.util.HashMap;

/**
 * Represents a to-do task. There is no deadline or timeslot associated.
 */
final class ToDo extends Task {

    /**
     * Initializes a to-do item, with a description.
     *
     * @param description The description of the task.
     */
    protected ToDo(String description) {
        super(description);
    }

    protected ToDo(HashMap<String, Object> infoTable) {
        super(infoTable);
    }


    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    @Override
    protected TaskType getType() {
        return TaskType.TODO;
    }

    @Override
    protected HashMap<String, Object> getInfoTable() {

        var infoTable = this.initializeInfoTable();
        return infoTable;
    }

}
