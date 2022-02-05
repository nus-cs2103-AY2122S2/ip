package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents a Deadline which is a kind of Task.
 * Encapsulates an additional LocalDate and LocalTime attribute
 * which represents the deadline of the Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalTime time;
    private LocalDate deadline;

    /**
     * Creates a new Deadline that is marked as not done.
     * 
     * <p>Accepts a deadlineDate string with pattern "yyyy-MM-dd" or "yyyy-MM-dd HHmm".</p>
     * 
     * @param task The description of the task.
     * @param deadlineDate The string representation of the deadline.
     * @throws DukeException If deadlineDate does not have the expected pattern.
     */
    public Deadline(String task, String deadlineDate) throws DukeException {
        super(task);
        String[] datetime = deadlineDate.split(" ");

        try {
            deadline = LocalDate.parse(datetime[0], DATE_FORMATTER);
            time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format!" 
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    /**
     * Creates a new Deadline that is marked as not done.
     * 
     * @param task The description of the task.
     * @param deadline Represents the date by which the task is due. 
     * @param time Represents the time (if any) by which the task is due. 
     */
    public Deadline(String task, LocalDate deadline, LocalTime time) {
        super(task);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Constructor to create a Deadline instance.
     * 
     * <p>This constructor accepts an additional isDone boolean to initialize 
     * a task that has been marked/unmarked as done. </p>
     * 
     * <p>Accepts a deadlineDate string with pattern "yyyy-MM-dd HHmm" or "yyyy-MM-dd".</p>
     * 
     * @param task The description of the task.
     * @param isDone Marks the task as done if true.
     * @param deadlineDate The string representation of the deadline.
     * @throws DukeException If deadlineDate does not have the expected pattern.
     */
    public Deadline (String task, boolean isDone, String deadlineDate) throws DukeException {
        this(task, deadlineDate);
        this.isDone = isDone;
    }

    /**
     * Formats a Deadline instance to be stored in an external file.
     */
    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        String deadlineString = deadline.format(DATE_FORMATTER);
        String timeString = time == null ? "" : " " + time.format(TIME_FORMATTER);
        return String.format("D | %d | %s | %s%s\n", 
                i, this.task, deadlineString, timeString);
    }

    /**
     * Returns the string representation of a Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.deadline.format(dateFormatter);
        String time = this.time == null ? "" : " " + this.time.format(timeFormatter);
        return String.format("[D]%s %s (by: %s%s)", this.statusString(), this.task, date, time);
    }

}
