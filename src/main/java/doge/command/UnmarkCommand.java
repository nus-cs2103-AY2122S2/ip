package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String s) {
        super(s);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.details.isEmpty()) {
            throw new DogeException("How am I suppose to unmark something without knowing which task?");
        }

        int pos;
        try {
            pos = Integer.parseInt(this.details);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer? Input a valid task no.");
        }

        if (pos > tasks.size() || pos < 0) {
            throw new DogeException("How am I supposed to unmark something that doesn't exist?");
        } else {
            if (!tasks.getTask(pos - 1).isDone()) {
                throw new DogeException("Your task has been unmarked before...");
            }

            tasks.unmarkTask(pos - 1);
            this.task = tasks.getTask(pos - 1);
        }
    }

    @Override
    public String toString() {
        return "Knew you didnt't finish that task, I've already unmarked it for you!";
    }
}
