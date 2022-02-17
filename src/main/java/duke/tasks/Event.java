package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Events are tasks happening at a point in time.
 */
public class Event extends Task {

    private LocalDateTime date;

    /**
     * Constructor for Event.
     *
     * @param content Description of Event.
     * @param date date and time of Event.
     */
    public Event(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    /**
     * Adds Event indicator at the front and the date time to the back of Task string.
     *
     * @return Formatted string representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date.toString().replace("T", " "));
    }

    /**
     * Adds Event indicator and date time to save data.
     *
     * @return Data to write into save file.
     */
    public String toSaveData() {
        return String.format("E|%s|%s\n", super.toSaveData(), this.date.toString().replace("T", " "));
    }

    /**
     * Converts String in a pattern into LocalDateTime object.
     *
     * @param datetime String to be converted into LocalDateTime.
     * @return LocalDateTime representing the date and time.
     */
    private static LocalDateTime parseDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }

    /**
     * Recreate Event from save data.
     *
     * @param savedData Event Data in save file.
     * @return Event that was saved.
     */
    public static Event createFromData(String savedData) {
        String[] parsedSavedData = savedData.split("\\|");
        Event newEvent = new Event(parsedSavedData[2], parseDateTime(parsedSavedData[3]));
        if (parsedSavedData[1].equals("1")) {
            newEvent.markAsDone();
        }
        return newEvent;
    }
}
