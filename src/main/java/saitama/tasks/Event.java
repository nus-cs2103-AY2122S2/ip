package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;

/**
 * An event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Initialises an undone event task.
     *
     * @param description The details of the event task.
     * @param at The event location.
     */
    public Event(String description, String at, RecursiveTag recursiveTag) {
        super(description, recursiveTag);
        this.at = at;
    }

    /**
     * Initialises an event task.
     *
     * @param description The description of the event task.
     * @param at The event location.
     * @param isDone Whether the task is done.
     */
    public Event(String description, String at, boolean isDone, RecursiveTag recursiveTag) {
        super(description, isDone, recursiveTag);
        this.at = at;
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file.
     * @throws IOException if there is an error writing the file.
     */
    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        String recursiveTag = this.recursiveTag == null ? "--" : this.recursiveTag.toString();
        fw.write(String.format("E %s %s %s /at %s\n", isDone, recursiveTag, description, at));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
