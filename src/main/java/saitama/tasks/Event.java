package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import saitama.tags.RecurFrequency;

/**
 * An event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Initialises an undone event task.
     *
     * @param description The details of the event task
     * @param at The event location
     * @param recurFrequency The frequency of recurrence of the event task
     */
    public Event(String description, String at, RecurFrequency recurFrequency) {
        super(description, recurFrequency);
        this.at = at;
    }

    /**
     * Initialises an event task.
     *
     * @param description The description of the event task
     * @param at The event location
     * @param isDone Whether the task is done
     * @param recurFrequency The frequency of recurrence of the event task
     * @param lastResetDate The last reset date of the task
     */
    public Event(String description, String at, boolean isDone,
                 RecurFrequency recurFrequency, LocalDate lastResetDate) {
        super(description, isDone, recurFrequency, lastResetDate);
        this.at = at;
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file
     * @throws IOException if there is an error writing the file
     */
    public void saveTask(FileWriter fw) throws IOException {
        fw.write(String.format("E %s %s %s %s /at %s\n", isDone, recurFrequency, lastResetDate, description, at));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
