import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * EventTask task which inherits from Task class.
 */
public class EventTask extends Task {

    /** Duration of event. */
    private LocalDateTime at;

    /**
     * Constructor for EventTask class.
     *
     * @param desc Description of EventTask.
     * @param at Duration of EventTask.
     */
    public EventTask(String desc, LocalDateTime at) {
        super(desc);
        this.at = at;
    }

    /**
     * String representation of EventTask for saving.
     *
     * @return String representation of EventTask for saving.
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + at;
    }

    /**
     * String representation of EventTask.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, K:mma");
        return "[E]" + super.toString() + " (at: " + this.at.format(formatDateTime)  + ")";
    }
}
