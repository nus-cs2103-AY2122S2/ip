import java.time.LocalDate;
import java.time.LocalTime;

class Deadline extends Task implements DateTimeInterface {
    private final LocalDate date;

    public Deadline(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    public Deadline(String task, boolean complete, LocalDate date) {
        super(task, complete);
        this.date = date;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[D][x] " + super.getTaskName() + "(by: " + this.date.toString() + ")";
        } else {
            return "[D][ ] " + super.getTaskName() + "(by: " + this.date.toString() + ")";
        }
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }
}
