package seedu.commands;

import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;
import seedu.duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Command {

    protected boolean isExit = false;
    public abstract void input(String inst) throws DukeException;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");

    public int checkIdx(String idx) throws DukeException {
        checkExist(idx);
        try {
            return Integer.parseInt(idx) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("That is not a number.");
        }
    }

    public String checkExist(String inst) throws DukeException {
        if (inst.equals("")) {
            throw new DukeException("No instruction in command entered");
        } else {
            return inst;
        }
    }

    public LocalDateTime checkDateTime(String dateInString) throws DukeException {
        try {
            return LocalDateTime.parse(dateInString.toUpperCase(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("That date is in the wrong format\nNeeds to follow this example: 1/3/2022 07:00 PM");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
