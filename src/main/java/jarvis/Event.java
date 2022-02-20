package jarvis;

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

    public String getVenue() {
        return this.venue;
    }
    /**
     * This method is used to format a Event object into a String which can then be stored in the text file.
     *
     * @return This returns the String which details the task and the venue.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + venue + ")";
    }

    /**
     * This method is used to compare if two Task objects are equal or not. Two Task objects are equal if
     * they have the same description and same status.
     *
     * @param o The object to compare with
     * @return boolean This returns true if they are equal and false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event task = (Event) o;
        boolean sameDescription = task.getDescription().equals(this.getDescription());
        boolean sameStatus = (task.isDone() == this.isDone());
        boolean sameVenue = (task.getVenue().equals(this.getVenue()));
        return sameDescription && sameStatus && sameVenue;
    }
}
