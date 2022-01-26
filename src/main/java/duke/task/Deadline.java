package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String desc, boolean isComp, LocalDate date){
        super(desc, isComp);
        this.date = date;
    }

    @Override
    public String toString(){
        String temp = "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return temp;
    }


}
