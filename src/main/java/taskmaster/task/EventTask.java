package taskmaster.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class encapsulates the information necessary for an Event task.
 */
public class EventTask extends Task {
    /** Time and Date of Event. **/
    private LocalDateTime eventDate;

    /**
     * Constructor for an EventTask.
     * @param taskName Name/Description of Task.
     * @param eventDate Time and Date of Event.
     */
    public EventTask(String taskName, LocalDateTime eventDate) {
        super(taskName);
        this.eventDate = eventDate;

    }

    /**
     * Format the string representation of EventTask objects.
     * @return String representation of EventTask objects.
     */
    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
        return "[E]" + super.toString() + " (at: " + this.eventDate.format(newFormat) + ")";
    }

    /**
     * Format the string representation of task objects for
     * saving and writing to the text file.
     */
    @Override
    public String saveToFileFormat() {
        String result = "E";
        String mark = this.isCompleted ? "1" : "0";
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return result + " | " + mark + " | " + taskName + " | " + eventDate.format(oldFormat);
    }
}
