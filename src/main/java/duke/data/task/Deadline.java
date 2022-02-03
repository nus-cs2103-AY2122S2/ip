package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Deadline), description,
     * and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd uuuu");
        return "[D]" + super.toString() + " (by: " + time.format(formatter) + ")";
    }

    /**
     * Provides the string representation of the task for data storage
     */
    @Override
    public String toStoreInfo() {
        String status = isDone ? "1" : "0";
        return "D | " + status + " | " + this.description + " | " + this.time + "\n";
    }

    /**
     * Compares two objects and checks whether they are the same.
     *
     * @param obj the other object to compared with
     * @return true if the two deadlines have the same description, status and time;
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline t = (Deadline) obj;
        return t.description.equals(this.description)
                && t.isDone == this.isDone
                && t.time.equals(this.time);
    }
}
