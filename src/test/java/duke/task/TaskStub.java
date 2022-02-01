package duke.task;

public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    /**
     * Returns the String representation of an Event
     *
     * @return String format of a Deadline
     */
    @Override
    public String toString() {
        return "[TEST]" + super.toString() + " (at: 24/7)";
    }

    /**
     * Returns the String format of how the Event will be saved in the text file
     *
     * @return String format of the Event task to be saved
     */
    public String toSave() {
        return String.format("Test | %d | %s | 24/7", this.isDone ? 1 : 0, this.description);
    }
}
