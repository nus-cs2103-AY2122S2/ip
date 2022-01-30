package spark.tasks.tasktypes;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime eventDateTime;
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("M-d-yyyy Hmm");
    private static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    /**
     * Creates a new Event.
     *
     * @param title The title of the event.
     * @param at When the event is taking place.
     */
    public Event(String title, LocalDateTime at) {
        super(title);
        this.eventDateTime = at;
    }

    /**
     * Generates a new Event nd initialises it with the
     * given completion status.
     *
     * @param isDone If the event is over.
     * @param title Description the title of the event.
     */
    public Event(boolean isDone, String title, String at) {
        super(isDone, title);
        this.eventDateTime = LocalDateTime.parse(at, inputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDateTime.format(outputDateTimeFormatter));
    }

    @Override
    public String encodeTask() {
        return String.format("E @@@ %b @@@ %s @@@ %s", super.isDone(), super.getTitle(), this.eventDateTime.format(inputDateTimeFormatter));
    }
}
