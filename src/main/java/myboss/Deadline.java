package myboss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task with a specified date as deadline. A deadline Object corresponds
 * to a task with a specified date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates an undone Deadline Task Object with the specified task name and deadline.
     *
     * @param taskName name of the task.
     * @param deadline deadline of task
     */
    public Deadline(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Creates a Deadline Task Object with the specified task name, deadline and whether task is done.
     *
     * @param taskName name of the task.
     * @param deadline deadline of task
     * @param isDone whether task is done.
     */
    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, "D", isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns string output of a Deadline Object when marked as done or not done.
     *
     * @param isDone whether task is to be marked as done or not done.
     * @return string output of Deadline Object when marked as done or not done.
     */
    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            setIsDone(true);
            return " Nice! I've marked this task as done:" + "\n" + "  "
                    + "     [" + this.getTaskType() + "]" + //[T]
                    "[" + (this.getIsDone() ? "X" : " ") + "] " + // [X]
                    this.getTaskName();
        } else {
            setIsDone(false);
            return "OK, I've marked this task as not done yet:" + "\n" + "  "
                    + "     [" + this.getTaskType() + "]" //[T]
                    + "[" + (this.getIsDone() ? "X" : " ") + "] " // [X]
                    + this.getTaskName();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
        String deadlineFormatted = deadline.format(formatter);
        return "[" + this.getTaskType() + "]"
                + "[" + (getIsDone() ? "X" : " ") + "] "
                + this.getTaskName()
                + " (by: " + deadlineFormatted + ")"
                + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline1 = (Deadline) o;
        return Objects.equals(getTaskType(), deadline1.getTaskType()) && Objects.equals(deadline, deadline1.deadline);
    }
}
