import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Deadline task
 *
 * @author Jan
 * @version 1.0
 */
public class Deadline extends Task {
    /**
     * The deadline
     */
    private LocalDate deadlineTime;

    /**
     * Constructor for Deadline objects
     *
     * @param deadlineName  the deadline name
     */
    public Deadline(String deadlineName, LocalDate deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns a String representation of the Deadline
     *
     * @return  Deadline in String
     */
    @Override
    public String toString() {
        String box1 = "[D]";
        String doneness;
        if (super.getDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String deadlineName = super.toString();
        String time = " (by: " + deadlineTimeToString() + ")";
        return box1 + doneness + deadlineName + time;
    }

    /**
     * Returns a string representation of the deadline time
     *
     * @return  deadline time in string
     */
    private String deadlineTimeToString() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}