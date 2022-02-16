package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * A type of task which contains the description and date of the task.
 */
public class Event extends Task {
    protected LocalDate dueDate;
    protected LocalTime time;
    protected String date;
    protected String place = "";

    /**
     * Constructor of this Event class.
     *
     * @param description description of the task
     * @param dueDate date and time of the event
     * @throws DukeException an exception when format of date is inputted wrong
     */
    public Event(String description, String dueDate) throws DukeException {
        super(description);
        boolean isLocationAdded = dueDate.contains(" >");
        if (isLocationAdded) {
            this.place = dueDate.split(" >")[1];
            this.date = dueDate.split(" >")[0];
        } else {
            this.date = dueDate;
        }
        try {
            this.setDueDateTime(dueDate);
        } catch (Exception e) {
            throw new DukeException("Wrong Date and Time format. yyyy-mm-dd HH:MM\n");
        }
    }

    /**
     * Formats date and time.
     *
     * @param dueDate the date and time of event
     */
    private void setDueDateTime(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate.split(" ")[0]);
        this.time = LocalTime.parse(dueDate.split(" ")[1]);
    }

    /**
     * Convert date into a string representation.
     *
     * @return a string form of the date
     */
    private String dateToString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Convert time into a string representation.
     *
     * @return a string form of the time
     */
    private String timeToString() {
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Obtain date of event.
     *
     * @return the String of the date
     */
    public String getDate() {
        return this.dateToString();
    }

    /**
     * Get unformatted data of the event for storage purposes.
     *
     * @return string of unformatted data
     */
    @Override
    public String getTaskData() {
        if (isPlaceValid()) {
            String location = " >" + place;
            return "[E]" + super.toString() + " (at: " + this.date + location + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + this.date + ")";
        }
    }

    /**
     * Get formatted data of the event for response purposes.
     *
     * @return string of formatted data
     */
    @Override
    public String toString() {
        if (isPlaceValid()) {
            String location = " | location: " + place;
            return "[E]" + super.toString() + " (at: " + this.dateToString() + " " + this.timeToString() + location + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + this.dateToString() + " " + this.timeToString() + ")";
        }
    }

    /**
     * Checks if there is a location added to the event
     *
     * @return true when there is a location attached to this event.
     */
    public boolean isPlaceValid() {
        return place.length() >= 1;
    }
}
