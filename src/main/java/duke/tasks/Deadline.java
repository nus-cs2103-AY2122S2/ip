package duke.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.exceptions.CorruptedSaveException;
import duke.exceptions.DukeException;

/**
 * Represents a Deadline which is a kind of Task.
 * Encapsulates an additional LocalDate and LocalTime attribute
 * which represents the deadline of the Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Optional<LocalTime> time;
    private LocalDate deadline;

    /**
     * Creates a new Deadline that is marked as not done.
     * <p>Accepts a deadlineDate string with pattern "yyyy-MM-dd" or "yyyy-MM-dd HHmm".</p>
     * @param task The description of the task.
     * @param deadlineDate The string representation of the deadline.
     * @throws DukeException If deadlineDate does not have the expected pattern.
     */
    public Deadline(String task, String deadlineDate) throws DukeException {
        super(task);
        String[] datetime = deadlineDate.split(" ");

        try {
            deadline = LocalDate.parse(datetime[0], DATE_FORMATTER);
            LocalTime time = datetime.length == 1 ? null : LocalTime.parse(datetime[1], TIME_FORMATTER);
            this.time = Optional.ofNullable(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format!"
                    + " Expected date and/or time in the following formats: \n"
                    + "yyyy-mm-dd | Example: 2022-06-26\n"
                    + "yyyy-mm-dd HHmm | Example: 2022-06-26 2359");
        }
    }

    /**
     * Creates a new Deadline that is marked as not done.
     * @param task The description of the task.
     * @param deadline Represents the date by which the task is due.
     * @param time Represents the time (if any) by which the task is due.
     */
    public Deadline(String task, LocalDate deadline, LocalTime time) {
        super(task);
        this.deadline = deadline;
        this.time = Optional.ofNullable(time);
    }

    /**
     * Constructor to create a Deadline instance.
     * <p>This constructor accepts an additional isDone boolean to initialize
     * a task that has been marked/unmarked as done. </p>
     * <p>Accepts a deadlineDate string with pattern "yyyy-MM-dd HHmm" or "yyyy-MM-dd".</p>
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
    @Override
    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        String deadlineString = deadline.format(DATE_FORMATTER);
        String timeString = time.map(localTime -> " " + localTime.format(TIME_FORMATTER)).orElse("");
        return String.format("D | %d | %s | %s%s\n",
                i, this.task, deadlineString, timeString);
    }

    /**
     * Converts a string from file format to Task.
     * <p> The inverse of toFileFormat().</p>
     * @param fileString The string to convert.
     * @throws CorruptedSaveException if unable to parse the string correctly
     */
    public static Deadline fromFileFormat(String fileString) throws CorruptedSaveException {
        String[] packetSections = fileString.split(" \\| ");
        try {
            boolean isDone = Integer.parseInt(packetSections[1]) == 1;
            String taskName = packetSections[2];
            String deadlineString = packetSections[3];
            return new Deadline(taskName, isDone, deadlineString);
        } catch (Exception e) {
            throw new CorruptedSaveException();
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { //short circuit if same instance
            return true;
        }

        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;

            return super.equals(otherDeadline)
                && this.deadline.equals(otherDeadline.deadline)
                && this.time.equals(otherDeadline.time);

        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of a Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date = this.deadline.format(dateFormatter);
        String time = this.time.map(localTime -> localTime.format(timeFormatter)).orElse("");
        return String.format("[D]%s %s (by: %s%s)", this.statusString(), this.task, date, " " + time);
    }

}
