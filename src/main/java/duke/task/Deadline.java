package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an instance of a Deadline
 * type of Task
 */
public class Deadline extends Task {

    /**
     * Store the date by which this instance
     * of the Deadline has to be completed
     */
    protected LocalDate date;

    /**
     * Constructor method for Deadline
     *
     * @param desc Description of Deadline
     * @param isComp Completion Status of Deadline
     * @param date Date by which Deadline has to be Completed
     */
    public Deadline(String desc, boolean isComp, LocalDate date){
        super(desc, isComp);
        this.date = date;
    }

    /**
     * toString method to provide String representation
     * of Deadline
     *
     * @return String representation of the Deadline
     */
    @Override
    public String toString(){
        String temp = "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return temp;
    }


}
