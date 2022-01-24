import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;
    protected String type;


    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    @Override
    public String taskDescriptionForFile() {
        return "D , 0 , " + this.description.trim() + " , " + this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(super.outputDateFormat) + ")";
    }
}