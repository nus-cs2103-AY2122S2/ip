package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * A to do.
 */
public class ToDo extends Task {
    /**
     * Initialises an undone to do task.
     *
     * @param description The details of the to do task.
     * @param recursiveTag The frequency recurrence of the event task.
     */
    public ToDo(String description, RecursiveTag recursiveTag) {
        super(description, recursiveTag);
    }

    /**
     * Initialises a to do task.
     *
     * @param description The details of the to do task.
     * @param isDone Whether the task is done.
     * @param recursiveTag The frequency recurrence of the event task.
     * @param lastResetDate The last reset date of the task.
     */
    public ToDo(String description, boolean isDone, RecursiveTag recursiveTag, LocalDate lastResetDate) {
        super(description, isDone, recursiveTag, lastResetDate);
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file.
     * @throws IOException if there is an error writing the file.
     */
    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        fw.write(String.format("T %s %s %s %s\n", isDone, recursiveTag, lastResetDate, description));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
