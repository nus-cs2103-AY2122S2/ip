package jose.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor that sets isDone to false.
     *
     * @param description Task description.
     */
    public Event(String description, String at) {
        super(description, false);
        String[] dateTime = at.split(" ");
        this.at = LocalDateTime.of(LocalDate.parse(dateTime[0]),
                LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * Constructor that sets all variables.
     *
     * @param description Task description
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at);
    }

    /**
     * Returns a formatted representation of a task that will be saved to the data file.
     *
     * @return A formatted string to be saved to the data file.
     */
    public String formatData() {
        return "E|" + super.formatData() + "|" + at.toString();
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
