package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * Deadlines are tasks due on a certain date.
 */
public class Deadline extends Task{

    private LocalDateTime date;

    /**
     * Constructor for Deadline.
     *
     * @param content Description of Deadline.
     * @param date date and time Deadline is due.
     */
    public Deadline(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Adds Deadline indicator at the front and the date time to the back of Task string.
     *
     * @return Formatted string representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.toString().replace("T", " "));
    }

    /**
     * Adds Deadline indicator and date time to save data.
     *
     * @return Data to write into save file.
     */
    public String toSaveData() {
        return String.format("D|%s|%s\n", super.toSaveData(), this.date.toString().replace("T", " "));
    }

    /**
     * Recreate Deadline from save data.
     *
     * @param savedData Deadline Data in save file.
     * @return Deadline that was saved.
     */
    public static Deadline createFromData(String savedData) {
        Parser parser = new Parser();
        String[] parsedSavedData = savedData.split("\\|");
        Deadline newDeadline = new Deadline(parsedSavedData[2], parser.parseSaveDateTime(parsedSavedData[3]));
        if (parsedSavedData[1].equals("1")) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }
}
