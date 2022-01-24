package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final String DEADLINE_STRING;
    private final LocalDateTime DEADLINE;

    public Deadline(String description, String deadLine) throws DukeException {
        super(description);
        this.DEADLINE = this.parseDeadline(deadLine);
        this.DEADLINE_STRING = this.formatDeadline();
    }

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

    private String formatDeadline() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return DEADLINE.format(form);
    }

   @Override
    public String formatSave() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String date = this.DEADLINE.format(form);
        return "D |" + (super.isDone ? "1| " : "0| ") + super.description + " /by " + date;
    }

    /*
     * Customized toString method for duke.task.Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.DEADLINE_STRING + ")";
    }
}
