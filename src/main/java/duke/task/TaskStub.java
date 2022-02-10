package duke.task;

public class TaskStub extends Task {
    public final String description;

    /**
     * Constructor for Task Stub.
     *
     * @param description description of Task Stub.
     */
    public TaskStub(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String track() {
        return "[ ]";
    }

    @Override
    public String getName() {
        return this.description;
    }
}
