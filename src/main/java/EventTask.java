import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final String taskTitle;
    private final String taskType = "E";
    private boolean markedStatus;
    private LocalDateTime deadline;

    public EventTask(String taskTitle, LocalDateTime deadline) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    public Task markTask() {
        if (!this.markedStatus) {
            this.markedStatus = true;
        }

        return this;
    }

    public Task unmarkTask() {
        if (this.markedStatus) {
            this.markedStatus = false;
        }

        return this;
    }

    public boolean isMarked() {
        return this.markedStatus;
    }

    @Override
    public String toString() {
        String mark = this.markedStatus ? "[X]" : "[ ]";
        String deadlineText = "(at: " + deadline.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm a")) + ")";
        String toReturn = "[E]" + mark + " " + this.taskTitle + " " + deadlineText;
        return toReturn;
    }
}
