package duke;

/**
 * Events are tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */

public class Events extends Task {

    private final DateTime duration;


    public Events(String description, DateTime duration) {
        super(description);
        this.duration = duration;
    }

    public String getDateTimeForStorage() {
        return this.duration.dateTimeForStorage();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration.toString() + ")";
    }
}
