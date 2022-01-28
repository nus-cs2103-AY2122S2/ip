package Duke.command;

import Duke.exception.DukeException;

import Duke.task.Task;

import Duke.util.TaskList;
import Duke.util.Storage;
import Duke.util.Ui;

public abstract class Command {

    /**
     * Executes command by printing exit message.
     *  @param taskList  Duke.Duke.util.TaskList of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}

