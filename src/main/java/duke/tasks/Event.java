package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import duke.exceptions.DukeException;

/**
 * Represents an event task.
 * Stores the date, start time and end time of the event.
 */
public class Event extends Task {

    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Creates an instance of an Event object.
     *
     * @param description the details of the event.
     * @param date the date of the event.
     * @param startTime the start time of the event.
     * @param startTime the end time of the event.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + startTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + " to "
                + endTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + ")";
    }

    /**
     * The factory function of the Event task. Throws an exception
     * if the provided tokens does not satisfy the required format to create
     * an Event task.
     *
     * @param tokens the specification of the Event task which contains
     *               the description and start time to end time of the task.
     */
    protected static Event createTask(String[] tokens) throws DukeException {
        String description = createDescription(tokens);
        if (description.equals("")) {
            throw new DukeException("The description of an event task cannot be empty!");
        }

        LocalDate date = createDate(tokens);
        LocalTime[] timings = createTime(tokens);
        LocalTime startTime = timings[0];
        LocalTime endTime = timings[1];

        return new Event(description, date, startTime, endTime);
    }

    private static String createDescription(String[] tokens) {
        String description = "";
        for (int idx = 0; idx < tokens.length; idx++) {
            if (tokens[idx].equals("event")) {
                continue;
            } else if (tokens[idx].equals("/at")) {
                idx++;
                break;
            }
            description += tokens[idx] + " ";
        }
        return description.trim();
    }

    private static LocalDate createDate(String[] tokens) throws DukeException {
        int idx = 0;
        for (; idx < tokens.length; idx++) {
            if (tokens[idx].equals("/at")) {
                break;
            }
        }
        try {
            String dateString = tokens[idx + 1];
            return LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new DukeException("Please specify a valid date!");
        }
    }

    private static LocalTime[] createTime(String[] tokens) throws DukeException {
        int idx = 0;
        for (; idx < tokens.length; idx++) {
            if (tokens[idx].equals("/at")) {
                break;
            }
        }
        try {
            String[] timeString = tokens[idx + 2].split("-");
            LocalTime startTime = LocalTime.parse(timeString[0]);
            LocalTime endTime = LocalTime.parse(timeString[1]);
            return new LocalTime[]{startTime, endTime};
        } catch (Exception e) {
            throw new DukeException("Please specify a valid start time and end time! (hh:mm)");
        }
    }
}
