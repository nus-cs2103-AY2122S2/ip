package duke.task;

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


    /**
     * Returns the icon that represents the type of the task.
     *
     * @return The icon.
     */
    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns the type of the current task.
     *
     * @return The type of the task.
     */
    @Override
    protected TaskType getType() {
        return TaskType.TODO;
    }


    /**
     * Returns a hash map that contains the data to be stored into hard disk.
     *
     * @return The hash map containing the data.
     */
    @Override
    public HashMap<String, Object> getInfoTable() {

        var infoTable = this.initializeInfoTable();
        return infoTable;
    }

}
