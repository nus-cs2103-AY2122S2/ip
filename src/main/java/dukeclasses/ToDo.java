package dukeclasses;

/**
 * Represents a Task item which needs to be completed. It can be marked as done or unmarked as not done.
 */
public class ToDo extends Task{

    /**
     * Class constructor to instantiate an instance of ToDo.
     *
     * @param description String that describes the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a description of the task and its status.
     * Identity used in taskList.
     *
     * @return String that represent the description of the task and its status(i.e. mark or unmarked).
     */
    @Override
    public String identify() {
        if (super.getIsDone()) {
            return String.format("[T][X] %s\n", super.getDescription());
        } else {
            return String.format("[T][ ] %s\n", super.getDescription());
        }
    }

}
