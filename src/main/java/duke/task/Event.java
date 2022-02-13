package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Event
 *
 * Date input format: eg. "/at 2/12/2019"
 */
public class Event extends Task {
    private LocalDate date;
    private String stringTime;

    public Event(String input) {
        super(input.substring(6, input.indexOf("/")));
        this.stringTime = input.substring(input.indexOf("/") + 4).trim();
        this.date = LocalDate.parse(stringTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    public String getFullDateTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        }

        Event currTask = (Event) o;
        boolean eventsEqual = currTask.description.equals(this.description)
                && currTask.date.equals(this.date);
        if (eventsEqual) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + ("(at: " + this.getFullDateTime() + ")");
    }
}
