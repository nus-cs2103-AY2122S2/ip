package seedu.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;

public abstract class Command {

    protected boolean isExit = false;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
    public abstract void input(String inst) throws DukeException;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Converts index of list in string form into integer form.
     * @param idx Index of tasklist in string form
     * @return Index of tasklist in integer form.
     * @throws DukeException Input is not an integer.
     */
    public int checkIdx(String idx) throws DukeException {
        checkExist(idx);
        try {
            return Integer.parseInt(idx) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("That is not a number.");
        }
    }

    /**
     * Checks whether instruction is blank
     * @param inst The string directly after the command type from the input.
     * @return The string directly after the command type from the input.
     * @throws DukeException There was no string after the command type from the input.
     */
    public String checkExist(String inst) throws DukeException {
        if (inst.equals("")) {
            throw new DukeException("No instruction in command entered");
        } else {
            return inst;
        }
    }

    /**
     * Converts datetime in string to datetime in LocalDateTime format.
     * @param dateInString Datetime in string form
     * @return DateTime in LocalDateTime format
     * @throws DukeException Cannot parse string into LocalDateTime format.
     */
    public LocalDateTime checkDateTime(String dateInString) throws DukeException {
        try {
            return LocalDateTime.parse(dateInString.toUpperCase(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("That date is in the wrong format\nNeed to follow this example: 1/3/2022 07:00 PM");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
