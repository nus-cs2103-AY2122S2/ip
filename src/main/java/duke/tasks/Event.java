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
        boolean found = false;
        String item = "";
        String dateString = "";
        String endString = "";
        int idx = 0;

        for (; idx < tokens.length; idx++) {
            if (tokens[idx].equals("event")) {
                continue;
            } else if (tokens[idx].equals("/at")) {
                idx++;
                break;
            }
            item += tokens[idx] + " ";
        }

        if (item.equals("")) {
            throw new DukeException("The description of an event task cannot be empty!");
        }

        LocalDate date;
        LocalTime startTime;
        LocalTime endTime;
        try {
            date = LocalDate.parse(tokens[idx]);
            idx++;
        } catch (Exception e) {
            throw new DukeException("Please specify a valid date!");
        }

        try {
            String[] timeString = tokens[idx].split("-");
            startTime = LocalTime.parse(timeString[0]);
            endTime = LocalTime.parse(timeString[1]);
        } catch (Exception e) {
            throw new DukeException("Please specify a valid start time and end time! (hh:mm)");
        }
        return new Event(item.trim(), date, startTime, endTime);
    }
}
