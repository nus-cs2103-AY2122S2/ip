package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class for todo type tasks
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo task
     * @param detail details of task to be added
     */
    public ToDo(String detail) {
        super(detail);
    }

    /**
     * Returns the due date of the task
     * @return Date if applicable
     */
    @Override
    public String getDate() {
        return "No Date";
    }

    /**
     * returns a string representation for the type of task
     * @return String representing the type of task
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Updates the details of the task
     */
    @Override
    public void updateDetail(String update) {
        this.detail = update;
    }

    /**
     * Does nothing since todo has no date
     */
    @Override
    public void updateDate(LocalDateTime update) {
    }

    /**
     * returns string representation of Task with mark status date and detail
     * @return String representation of Task
     */
    @Override
    public String toString() {
        if (isMarked) {
            return "[T][X] " + detail;
        } else {
            return "[T][ ] " + detail;
        }
    }
}
