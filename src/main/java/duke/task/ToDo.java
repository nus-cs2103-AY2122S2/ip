package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String done = isComplete() ? "[X]" : "[ ]";
        return "[T]" + done + getTaskName();
    }
}
