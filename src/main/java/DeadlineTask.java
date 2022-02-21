import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    LocalDateTime byDate;

    private final static DateTimeFormatter fm = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
    public DeadlineTask(String description, boolean isCompleted, LocalDateTime byDate){
        super(description, isCompleted);
        this.byDate = byDate;
    }

    public LocalDateTime getByDate() {
        return byDate;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString() + "(by: " + byDate.format(fm) + ")";
    }
}
