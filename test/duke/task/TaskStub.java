package duke.task;

public class TaskStub extends Task {
    public String description;
    public boolean isDone;

    public TaskStub(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "taskTest";
    }

    @Override
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public void unmarkDone() {
        this.isDone = false;
    }
}
