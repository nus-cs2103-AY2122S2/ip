package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * The CommandMark class contains basic attributes and
 * behaviours of a Mark Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandMark extends Command {
    /**
     * Index of Task to be marked
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
    public CommandMark(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of a Mark Command.
     * The TaskList marks the task, the Storage saves it
     * and the Ui lets the user know that the task was
     * executed successfully.
     *
     * @param tasks - for TaskList to mark Task
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
     * @throws DukeException - thrown if tasklist does not contain tasks
     *                         or error saving data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.mark(this.taskNum);
        storage.save(tasks);
        ui.showTaskMarked();
    }
}
