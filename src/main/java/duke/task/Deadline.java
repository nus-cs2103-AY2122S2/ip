package duke.task;

import java.util.Objects;
import java.util.Optional;

/**
 * Class for deadline task
 */
public class Deadline extends Task {

    private String taskDeadline;

    public Deadline(String task, String taskDeadline) {
        super(task);
        this.taskDeadline = taskDeadline;
    }

    /**
     * Get the task typ of deadline task
     * @return
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Constructor for deadline task class
     * @param status
     * @param task
     * @param taskDeadline
     */
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
