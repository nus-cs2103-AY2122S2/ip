package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDateTime;

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

    @Override
    public LocalDateTime getDate() {
        return this.date;
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
     * Recreate Event from save data.
     *
     * @param savedData Event Data in save file.
     * @return Event that was saved.
     */
    public static Event createFromData(String savedData) {
        Parser parser = new Parser();
        String[] parsedSavedData = savedData.split("\\|");
        Event newEvent = new Event(parsedSavedData[2], parser.parseSaveDateTime(parsedSavedData[3]));
        if (parsedSavedData[1].equals("1")) {
            newEvent.markAsDone();
        }
        return newEvent;
    }
}
