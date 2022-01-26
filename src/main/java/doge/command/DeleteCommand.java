package doge.command;

import doge.*;
import doge.exception.DogeException;

public class DeleteCommand extends Command {
    public DeleteCommand(String s) {
        super(s);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.details.isEmpty()) {
            throw new DogeException("How am I suppose to delete something without knowing which task?");
        }

        int pos;
        try {
            pos = Integer.parseInt(this.details);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer? Input valid task no.");
        }

        if (pos > tasks.size() || pos < 0) {
            throw new DogeException("How am I supposed to delete something that doesn't exist?");
        } else {
            this.task = tasks.getTask(pos - 1);
            tasks.deleteTask(pos - 1);
        }
    }

    @Override
    public String toString() {
        return "I've already deleted for you! You're welcome.";
    }
}