package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Event), description,
     * and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }

    /**
     * Provides the string representation of the task for data storage
     */
    public String toStoreInfo() {
        String status = isDone ? "1" : "0";
        return "E | " + status + " | " + this.description + " | " + this.time + "\n";
    }

    /**
     * Compares two objects and checks whether they are the same.
     *
     * @param obj The other object to be compared with.
     * @return True if the two events have the same description, status and time;
     *         False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }

        Event t = (Event) obj;
        return t.description.equals(this.description)
                && t.isDone == this.isDone
                && t.time.equals(this.time);
    }
}
