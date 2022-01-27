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

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String genDoneSymbol() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toInputString() {
        return "task " + description;
    }

    public String toString() {
        return "[" + genDoneSymbol() + "] " + description;
    }
}
