package duke.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.exceptions.CorruptedSaveException;
import duke.exceptions.DukeException;

/**
 * Represents an Event which is a kind of Task.
 * Encapsulates an additional LocalDate and LocalTime attribute
 * which represents when the Event occur.
 */
public class Event extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate startDate;
    private Optional<LocalTime> time;

    /**
     * Creates a new Event that is marked as not done by default.
     * 
     * <p>Accepts a startDateString with pattern "yyyy-MM-dd" or "yyyy-MM-dd HHmm".</p>
     * 
     * @param task The description of the task.
     * @param startDateString The string representation of when the event occurs.
     * @throws DukeException If startDateString does not have the expected pattern.
     */
    public Event (String task, String startDateString) throws DukeException {
        super(task);
        String[] datetime = startDateString.split(" ");
        
        try {
            startDate = LocalDate.parse(datetime[0], DATE_FORMATTER);
            LocalTime time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
            this.time = Optional.ofNullable(time);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Invalid date/time format!" 
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    /**
     * Creates a new Event that is marked as not done.
     * 
     * 
     * @param task The description of the task.
     * @param startDate Represents the date at which the event occurs. 
     * @param time Represents the time (if any) by which the event occurs. 
     */
    public Event(String task, LocalDate startDate, LocalTime time) {
        super(task);
        this.startDate = startDate;
        this.time = Optional.ofNullable(time);
    }

    /**
     * Constructor to create an Event instance.
     * 
     * <p>This constructor accepts an additional isDone boolean to initialize 
     * a task that has been marked/unmarked as done. </p>
     * 
     * <p>Accepts a startDate string with pattern "yyyy-MM-dd HHmm" or "yyyy-MM-dd".</p>
     * @param task The description of the task.
     * @param isDone Marks the task as done if true.
     * @param startDate The string representation of when the event occurs.
     * @throws DukeException If startDate does not have the expected pattern.
     */
    public Event (String task, boolean isDone, String startDate) throws DukeException {
        this(task, startDate);
        this.isDone = isDone;
    }

    /**
     * Formats an Event instance to be stored in an external file.
     */
    @Override
    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        String startDateString = startDate.format(DATE_FORMATTER);
        String timeString = time.map(localTime -> localTime.format(TIME_FORMATTER)).orElse("");
        return String.format("E | %d | %s | %s%s\n", 
                i, this.task, startDateString, timeString);
    }

    /**
     * Converts a string from file format to Task.
     * 
     * <p> The inverse of `toFileFormat()`.</p>
     * 
     * @param fileString The string to convert.
     * @throws CorruptedSaveException if unable to parse the string correctly
     */
    public static Event fromFileFormat(String fileString) throws CorruptedSaveException {
        String[] packetSections = fileString.split(" \\| ");
        try {
            boolean isDone = Integer.parseInt(packetSections[1]) == 1;
            String taskName = packetSections[2];
            String startDateString = packetSections[3];
            return new Event(taskName, isDone, startDateString);
        } catch (Exception e) {
            throw new CorruptedSaveException();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { //short circuit if same instance
            return true;
        }

        if (other instanceof Event) {
            Event otherEvent = (Event) other;

            return super.equals(otherEvent)
                && this.startDate.equals(otherEvent.startDate)
                && this.time.equals(otherEvent.time);
            

        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of an Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.startDate.format(dateFormatter);
        String time = this.time.map(localTime -> localTime.format(timeFormatter)).orElse("");
        return String.format("[E]%s %s (at: %s%s)", this.statusString(), this.task, date, time);
    }
}
