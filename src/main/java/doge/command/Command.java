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

    /**
     * Executes the current command.
     *
     * @param tasks the TaskList that keeps an arrayList of the current list of tasks
     * @param ui the Ui that Doge bot is using
     * @param storage the storage to save the list of tasks to
     * @throws DogeException if there is a failure in TaskList or Storage method calls
     */
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
