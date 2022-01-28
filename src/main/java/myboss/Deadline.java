package myboss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String taskType = "D";
    LocalDate deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, "D", isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.isDone = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
        String deadlineFormatted = deadline.format(formatter);
        return "[" + this.taskType + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.taskName +
                " (by: " + deadlineFormatted + ")" +
                "\n";
    }
}
