package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.task.Task;

/**
 * Encapsulates the functionalities of the different commands understandable by Doge bot.
 */
public abstract class Command {
    protected Task task;
    protected String details;
    protected boolean isExit;

    public Command() {

    }

    public Command(String d) {
        this.details = d;
        this.isExit = false;
    }

    public Command(Task task) {
        this.task = task;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException;

    public boolean haveTask() {
        return this.task != null;
    }

    public Task getTask() {
        return this.task;
    }

    public void setExit(boolean b) {
        this.isExit = b;
    }

    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public abstract String toString();
}
