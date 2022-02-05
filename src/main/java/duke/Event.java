package duke;

/**
 * Represents an event with a venue.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Event extends Task {
    protected String venue;

    /**
     * Returns an Event object with a description of the task and where the venue is.
     *
     * @param description description of the event
     * @param venue venue where the event is held
     */
    public Event(String description, String venue) {
        super(description);
        this.venue = venue;
    }

    public Event(String description, String venue, boolean isDone) {
        super(description, isDone);
        this.venue = venue;
    }

    /**
     * This method is used to format a Event object into a String which can then be stored in the text file.
     *
     * @return This returns the String which details the task and the venue.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + venue +")";
    }
}
