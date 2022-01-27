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

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toInputString() {
        return "todo " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
