package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;


    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String taskDescriptionForFile() {
        return "D , 0 , "
                + this.description.trim() + " , " + this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(super.outputDateFormat) + ")";
    }
}