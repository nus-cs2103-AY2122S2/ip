package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.UiForGUI;
import task.TaskList;

/**
 * The Command class is an abstract class to represent an actual command by the user.
 */
public abstract class Command {
    protected String command;
    protected String[] tokenizedCommand;
    protected boolean isRun;

    public Command(String command, String[] tokenizedCommand) {
        this.command = command;
        this.tokenizedCommand = tokenizedCommand;
        this.isRun = true;
    }

    /**
     * Executes the current command.
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, UiForGUI ui, Storage storage) throws DukeException;

    public boolean isRunProgram() {
        return this.isRun;
    }
}
