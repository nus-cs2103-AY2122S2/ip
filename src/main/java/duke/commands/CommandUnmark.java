package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandUnmark class contains basic attributes and
 * behaviours of a Unmark Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandUnmark extends Command {
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
    public CommandUnmark(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of an Unmark Command.
     * The TaskList unmarks the task, the Storage saves it
     * and the Ui lets the user know that the task was
     * executed successfully.
     *
     * @param tasks - for TaskList to unmark Task
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
     * @throws DukeException - thrown if TaskList does not contain tasks
     *                         or error saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmark(this.taskNum);
        storage.save(tasks);
        ui.showTaskUnmarked();
    }
}
