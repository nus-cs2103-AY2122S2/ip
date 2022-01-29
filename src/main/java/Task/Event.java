package Task;

import Task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * a type of task that contains date
 */
public class Event extends Task {

    boolean useLocalDate = false;
    protected LocalDate at;
    protected String strAt = "";

    /**
     * Create a new Event class
     *
     * @param description string of the description
     * @param strAt string of the date
     */
    public Event(String description, String strAt) {
        super(description);
        strAt = strAt.trim();
        try {
            this.at = LocalDate.parse(strAt);
            useLocalDate = true;
        } catch (DateTimeParseException e){
            this.strAt = strAt;
        }
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    @Override
    public String saveString() {
        return "E" + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + at;
    }


    /**
     * string format for printing on the UI
     *
     * @return string format for printing on the UI
     */
    @Override
    public String toString() {
        if (useLocalDate) {
            return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + strAt + ")";
        }
    }
}