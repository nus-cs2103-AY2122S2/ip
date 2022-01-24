package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task{
    private final String DEADLINE_STRING;
    private final LocalDateTime DEADLINE;

    /**
     * Returns a deadline Task object and accepts a String as description and a String to indicate the deadline.
     *
     * @param description Description of the task.
     * @param deadLine Deadline of the task.
     */
    public Deadline(String description, String deadLine) throws DukeException {
        super(description);
        this.DEADLINE = this.parseDeadline(deadLine);
        this.DEADLINE_STRING = this.formatDeadline();
    }

    /**
     * Converts the deadline from a String to a LocalDateTime object and returns it.
     *
     * @param deadLine Deadline of the task.
     * @return deadLine as a LocalDateTime.
     */
    private LocalDateTime parseDeadline(String deadLine) throws DukeException{
        String[] temp = deadLine.split(" ",2);
        if(temp.length <= 1 || temp[1].length() < 4) {
            throw new DukeWrongInputFormatException("Format for deadline is wrong. Please refer to list of commands.");
        }
        try {
            return LocalDateTime.parse(temp[0] + "T" + temp[1].charAt(0) + temp[1].charAt(1)
                    + ":" + temp[1].charAt(2) + temp[1].charAt(3) + ":00");
        } catch (DateTimeParseException e) {
            throw new DukeWrongInputFormatException("Format for deadline is wrong. Please refer to list of commands.");
        }
    }

    /**
     * Returns a formatted String based on the pattern MMM dd yyyy HH:mm.
     *
     * @return Formatted String.
     */
    private String formatDeadline() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return DEADLINE.format(form);
    }

    /**
     * Returns a formatted String to be saved in a file.
     *
     * @return Formatted String for saving.
     */
   @Override
    public String formatSave() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String date = this.DEADLINE.format(form);
        return "D |" + (super.isDone ? "1| " : "0| ") + super.description + " /by " + date;
    }

    /**
     * Returns a String to display the type, the done status of task as well as the task description.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.DEADLINE_STRING + ")";
    }
}
