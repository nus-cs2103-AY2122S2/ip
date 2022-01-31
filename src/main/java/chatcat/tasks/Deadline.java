package chatcat.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The default Deadline class inherited from {@code Task}.
 *
 * @see Task
 * @see DateTimeFormatter
 * @see DateTimeFormatterBuilder
 */
public class Deadline extends Task {
    String deadline;
    LocalDate time;

    DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                    "" +
                            "[yyyy-MM-dd HH:mm:ss]" +
                            "[yyyy-MM-dd]" +
                            "[yyyy/MM/dd]" +
                            "[yyyy-MM-dd HH:mm a]"
            ));
    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

    /**
     * Creates a {@code Deadline} object using a specified description.
     *
     * @param deadline the description of this task.
     * @param time the time of the task.
     * @throws DateTimeException if user inputs an invalid time
     * @see LocalDate
     */
    public Deadline(String deadline, String time) {
        super(deadline);
        this.deadline = deadline;

        try {
            this.time = LocalDate.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    /**
     * Returns a representation in string of {@code Deadline} task.
     *
     * @return a representation in string of {@code Deadline} task.
     */
    @Override
    public String toString() {
        String temp = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + temp + ")";
    }
}
