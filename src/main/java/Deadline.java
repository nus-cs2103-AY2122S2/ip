import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadlineDate;

    public Deadline(String description, String deadlineDate){
        super(description, "D");
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    public String getDeadlineDate(){
        return this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription(), this.getDeadlineDate());
    }

    @Override
    public String toString() {
        return super.toString() + " (By: " + this.getDeadlineDate() + ")";
    }

}
