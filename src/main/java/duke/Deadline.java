package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Assigns desc, date and done to this instance.
     *
     * @param desc the task description.
     * @param date the date of the event.
     * @param isDone the current completion status of the task.
     */
    public Deadline(String desc, LocalDate date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        String str = "[D]";
        // Convert Current Date Format to MMM dd yyyy
        String customPattern = "MMM dd yyyy";
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(customPattern);
        String tempDate = customFormat.format(this.date);
        if (super.isDone) {
            str += "[X] " + super.desc + " (by: " + tempDate + ")";
        } else {
            str += "[ ] " + super.desc + " (by: " + tempDate + ")";
        }
        return str;
    }

    /**
     * Changes the string format of this object.
     *
     * @return a string in this format D,{done},{desc},{date}.
     */
    @Override
    public String changeFormat() {
        if (super.isDone) {
            return "D,1," + super.desc + "," + this.date;
        } else {
            return "D,0," + super.desc + "," + this.date;
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
