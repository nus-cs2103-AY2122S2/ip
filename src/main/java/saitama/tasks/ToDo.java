package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import saitama.tags.RecurFrequency;

/**
 * A to do.
 */
public class ToDo extends Task {
    /**
     * Initialises an undone to do task.
     *
     * @param description The details of the to do task
     * @param recurFrequency The frequency of recurrence of the event task
     */
    public ToDo(String description, RecurFrequency recurFrequency) {
        super(description, recurFrequency);
    }

    /**
     * Initialises a to do task.
     *
     * @param description The details of the to do task
     * @param isDone Whether the task is done
     * @param recurFrequency The frequency of recurrence of the event task
     * @param lastResetDate The last reset date of the task
     */
    public ToDo(String description, boolean isDone, RecurFrequency recurFrequency, LocalDate lastResetDate) {
        super(description, isDone, recurFrequency, lastResetDate);
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file
     * @throws IOException if there is an error writing the file
     */
    public void saveTask(FileWriter fw) throws IOException {
        fw.write(String.format("T %s %s %s %s\n", isDone, recurFrequency, lastResetDate, description));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
