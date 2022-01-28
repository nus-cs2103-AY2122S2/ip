package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A type of tasks, have a specific ddate
 */
public class Event extends Task {

    protected String at;
    private LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    /**
     * Transform the task into the format of data, to be stored into file data
     * @return A formatted string
     */
    @Override
    public String dataFormatOfTask() {
        String bool;
        if(this.isDone) {
            bool = "1";
        } else {
            bool = "0";
        }
        return "E | " + bool + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
