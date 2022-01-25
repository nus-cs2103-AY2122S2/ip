import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime timing;

    public Event (String task, LocalDateTime timing) throws DukeException {
        super(task);
        if (timing == null) {
            throw new DukeException("Try Again with correct format!\n");
        }
        this.timing = timing;
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: Date-> " + this.timing.getDayOfMonth() + " Month-> " +  this.timing.getMonth() + " Day-> " + this.timing.getDayOfWeek() + " Year-> " + this.timing.getYear() + " Time-> " + this.timing.getHour() + this.timing.getMinute() + ")";
    }
}
