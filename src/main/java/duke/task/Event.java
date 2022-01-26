package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;

    public Event(String desc, boolean isComp, LocalDate date){
        super(desc, isComp);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
