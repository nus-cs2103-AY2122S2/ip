package johnny;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate eventDate;

    public Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    /**
     * Overrides parent method to return
     * string that can be written to the text
     * file (formatted for Event objects)
     *
     * @return String to write to file.
     */
    @Override
    public String getTaskString() {
        return "EVENT" + "," + super.isDone + "," + super.description + "," + this.eventDate;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " +
                super.getDescription() + " (" +
                this.getEventDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
