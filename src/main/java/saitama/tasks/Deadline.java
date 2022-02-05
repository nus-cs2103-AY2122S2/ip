package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import saitama.exceptions.InvalidFormatException;

/**
 * A deadline task.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Initialises an undone deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task in dd/mm/yyyy format.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    public Deadline(String description, String by) throws InvalidFormatException {
        super(description);
        String[] date = by.split("/");
        if (date.length < 3) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
        try {
            LocalDate deadline = LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
            this.deadline = deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
    }

    /**
     * Initialises a deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task in dd/mm/yyyy format.
     * @param isDone Whether the task is done.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    public Deadline(String description, String by, boolean isDone) throws InvalidFormatException {
        super(description, isDone);
        String[] date = by.split("/");
        if (date.length < 3) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
        try {
            LocalDate deadline = LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
            this.deadline = deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please enter a valid deadline format! (dd/mm/yyyy)");
        }
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file.
     * @throws IOException if there is an error writing the file.
     */
    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        fw.write("D " + isDone + " " + this.description
                + " /by " + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
