import java.time.LocalDateTime;

public class DeadLines extends Task {
    LocalDateTime deadline;

    public DeadLines (String task, LocalDateTime deadline) throws DukeException {
        super(task);
        if (deadline == null) {
            throw new DukeException("Try Again with correct format!\n");
        }
        this.deadline = deadline;
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: Date-> " + this.deadline.getDayOfMonth() + " Month-> " + this.deadline.getMonth() + " Day-> " + this.deadline.getDayOfWeek() + " Year-> " + this.deadline.getYear() + " Time-> " + this.deadline.getHour() + this.deadline.getMinute() + ")";
    }
}
