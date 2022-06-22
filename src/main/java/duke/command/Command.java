package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import duke.exception.DukeException;

/**
 * A basic representation of the command.
 */
public abstract class Command {
    /**
     * A base method to execute command.
     *
     * @param tasks TaskList class
     * @param ui Ui class
     * @param storage Storage class
     * @throws DukeException if Duke-specified exception
     * @throws Exception if other exception
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, Exception;
}
