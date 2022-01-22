import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate d;

    DeadlineTask(String ss, LocalDate date) {
        this.taskName = ss;
        this.isDone = false;
        this.d = date;
    }

    DeadlineTask(String ss, boolean isDone, LocalDate date) {
        this.taskName = ss;
        this.isDone = isDone;
        this.d = date;
    }

    public LocalDate getDueDate() {
        return this.d;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.isDone?"X":" ", this.taskName, this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
