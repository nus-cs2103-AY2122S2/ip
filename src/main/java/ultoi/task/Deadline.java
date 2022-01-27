package ultoi.task;

import ultoi.command.Command;
import ultoi.command.AddCommand;
import ultoi.command.ByeCommand;
import ultoi.command.DeleteCommand;
import ultoi.command.ListCommand;
import ultoi.command.MarkCommand;

import ultoi.util.Ultoi;
import ultoi.util.UltoiUi;
import ultoi.util.UltoiException;
import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.Parser;
import ultoi.util.DateTime;

public class Deadline extends Task {
    protected DateTime dateTime;

    public Deadline(String description, String time) throws UltoiException {
        super(description);
        try {
            this.dateTime = new DateTime(time);
        } catch (UltoiException e) {
            throw e;
        }
    }

    public String toInputString() {
        return "deadline " + description + " /by " + dateTime.toInputString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.toString() + ")";
    }
}
