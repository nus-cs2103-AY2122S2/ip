package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import saitama.tags.RecurFrequency;

/**
 * A deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Initialises an undone deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task.
     * @param recurFrequency The frequency of recurrence the deadline task.
     */
    public Deadline(String description, LocalDateTime by, RecurFrequency recurFrequency) {
        super(description, recurFrequency);
        this.deadline = by;
    }

    /**
     * Initialises a deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task.
     * @param isDone Whether the task is done.
     * @param recurFrequency The frequency recurrence of the deadline task.
     * @param lastResetDate The last reset date of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone,
                    RecurFrequency recurFrequency, LocalDate lastResetDate) {
        super(description, isDone, recurFrequency, lastResetDate);
        this.deadline = by;
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file.
     * @throws IOException if there is an error writing the file.
     */
    public void saveTask(FileWriter fw) throws IOException {
        String isDone = getStatusIcon() == "X" ? "1" : "0";
        String recursiveTag = recurFrequency == null ? "--" : recurFrequency.toString();
        fw.write(String.format("D %s %s %s %s /by %s\n", isDone, recursiveTag, lastResetDate, description,
                deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
