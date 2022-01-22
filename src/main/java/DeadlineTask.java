import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate d;

    DeadlineTask(String ss, String date) {
        this.taskName = ss;
        this.isDone = false;
        this.d = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.isDone?"X":" ", this.taskName, this.d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
