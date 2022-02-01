package bobby;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected String at;

    /**
     * Constructor to create an instance of Event.
     * @param description description of the task.
     * @param at date/time of the occurrence of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        fw.write("E ; " + isDone + " ; " + description + " ; " + at + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
