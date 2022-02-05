package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandDelete class contains basic attributes and
 * behaviours of a Delete Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandDelete extends Command {
    /**
     * Index of Task to be unmarked
     */
    private final int taskNum;

    /**
     * Sole constructor.
     * Possible NumberFormatException during the parsing of
     * the input from String into int. Checks whether
     * the String input can be parsed into an int.
     *
     * @param str - raw task number input from the user
     * @throws DukeException - str cannot be parsed into an int
     */
    public CommandDelete(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of a Delete Command.
     * The TaskList deletes the task, the Storage saves it
     * and the Ui lets the user know that the task was
     * executed successfully.
     *
     * @param tasks - for TaskList to delete Task
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
     * @throws DukeException - thrown if TaskList does not contain tasks
     *                         or error saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.delete(this.taskNum);
        storage.save(tasks);
        ui.showTaskDeleted();
    }
}
