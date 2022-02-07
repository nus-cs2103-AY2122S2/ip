package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import saitama.exceptions.InvalidDateTimeException;
import saitama.exceptions.InvalidFormatException;

/**
 * A deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Initialises an undone deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task in dd/mm/yyyy format.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    public Deadline(String description, String by, RecursiveTag recursiveTag) throws
            InvalidFormatException, InvalidDateTimeException {
        super(description, recursiveTag);
        this.deadline = processDateTime(by);
    }

    /**
     * Initialises a deadline task.
     *
     * @param description The details of the deadline task.
     * @param by The deadline of the task in dd/mm/yyyy format.
     * @param isDone Whether the task is done.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    public Deadline(String description, String by, boolean isDone, RecursiveTag recursiveTag) throws
            InvalidFormatException, InvalidDateTimeException {
        super(description, isDone, recursiveTag);
        this.deadline = processDateTime(by);
    }

    /**
     * Returns a LocalDate object based on the by parameter.
     *
     * @param by The deadline in dd/mm/yyyy format.
     * @return LocalDate object corresponding to the provided date.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    private LocalDateTime processDateTime(String by) throws InvalidFormatException, InvalidDateTimeException {
        String[] dateTime = by.split(" ", 2);
        if (dateTime.length < 2) {
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
        }

        String date = dateTime[0];
        String time = dateTime[1];
        String[] splitDate = date.split("/");
        String[] splitTime = time.split(":");

        if (splitDate.length < 3 || splitTime.length < 2) {
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
        }

        try {
            int year = Integer.parseInt(splitDate[2]);
            int month = Integer.parseInt(splitDate[1]);
            int day = Integer.parseInt(splitDate[0]);
            int hour = Integer.parseInt(splitTime[0]);
            int minute = Integer.parseInt(splitTime[1]);
            LocalDateTime deadline = LocalDateTime.of(year, month, day, hour, minute);
            return deadline;
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
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
        String recursiveTag = this.recursiveTag == null ? "--" : this.recursiveTag.toString();
        fw.write(String.format("D %s %s %s /by %s\n", isDone, recursiveTag, description,
                deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
