package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Event extends Task {

    private LocalDate date;

    /**
     * Assigns desc, date and done to this instance.
     *
     * @param desc the task description.
     * @param date the date of the event.
     * @param isDone the current completion status of the task.
     */
    public Event(String desc, String date, boolean isDone) throws DukeException {
        super(desc, isDone);
        try {
            LocalDate formattedDate = LocalDate.parse(date);
            this.date = formattedDate;
        } catch (DateTimeParseException ex) {
            throw new DukeException("You have inserted an invalid date format.");
        }
    }

    @Override
    public String toString() {
        String str = "[E]";
        // Convert Current Date Format to MMM dd yyyy
        String customPattern = "MMM dd yyyy";
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(customPattern);
        String tempDate = customFormat.format(this.date);
        if (super.isDone) {
            str += "[X] " + super.desc + " (at: " + tempDate + ")";
        } else {
            str += "[ ] " + super.desc + " (at: " + tempDate + ")";
        }
        for (Tag tag: tags) {
            str = str + " " + tag;
        }
        return str;
    }

    /**
     * Changes the string format of this event object.
     *
     * @return a string in this format E,{done},{desc},{date}.
     */
    @Override
    public String changeFormat(TagList tagList) {
        String str = "";
        if (super.isDone) {
            str = str + "E,1," + super.desc + "," + this.date;
        } else {
            str = str + "E,0," + super.desc + "," + this.date;
        }
        for (Tag tag: tags) {
            str = str + "," + tagList.getList().indexOf(tag);
        }
        return str;
    }

    public LocalDate getDate() {
        return date;
    }
}
