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

public class Event extends Task {
    protected DateTime dateTime;

    public Event(String description, String time) throws UltoiException {
        super(description);
        try {
            this.dateTime = new DateTime(time);
        } catch (UltoiException e) {
            throw e;
        }
    }

    public String toInputString() {
        return "event " + description + " /at " + dateTime.toInputString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.toString() + ")";
    }
}
