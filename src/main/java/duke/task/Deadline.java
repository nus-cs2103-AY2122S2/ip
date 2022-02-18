package duke.task;

import java.util.Objects;
import java.util.Optional;

public class Deadline extends Task {

    private String taskDeadline;

    public Deadline(String task, String taskDeadline) {
        super(task);
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public Deadline(String status, String task, String taskDeadline) {
        super(task, Objects.equals(status, "1") ? true : false);
        this.taskDeadline = taskDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + taskDeadline + ")";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(taskDeadline);
    }


}
