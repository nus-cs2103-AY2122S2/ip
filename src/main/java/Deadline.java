import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadlineDate;

    public Deadline(String description, String deadlineDate){
        super(description);
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    public String getDeadlineDate(){
        return this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.getDeadlineDate() + ")";
    }

}
