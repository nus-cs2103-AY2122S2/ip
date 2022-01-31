package chatcat.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The default Event class. Inherited from {@code Task}.
 *
 * @see Task
 * @see DateTimeFormatter
 * @see DateTimeFormatterBuilder
 */
public class Event extends Task {
    String event;
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
     * Creates a {@code Event} object using a specified description.
     *
     * @param event the description of this task.
     * @param time the time of the task.
     * @throws DateTimeException if user inputs an invalid time
     * @see LocalDate
     */
    public Event(String event, String time) throws DateTimeException {
        super(event);
        this.event = event;
        System.out.println(time);

        try {
            this.time = LocalDate.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    /**
     * Returns a representation in string of {@code Event} task.
     *
     * @return a representation in string of {@code Event} task.
     */
    @Override
    public String toString() {
        String temp = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(at: " + temp + ")";
    }
}
