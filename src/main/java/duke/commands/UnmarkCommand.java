package duke.commands;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;

/**
 * UnmarkCommand is a Command.
 * This Command is used to Mark a certain Task as being not done.
 */
public class UnmarkCommand extends Command<Integer> {
    /**
     * Constructor for UnmarkCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList       the user's List of Tasks
     * @param numberToUnmark the item number to unmark
     * @throws DukeException when the item number to unmark does not exist
     */
    public UnmarkCommand(TaskList toDoList, Integer numberToUnmark) throws DukeException {
        this.runCommand(toDoList, numberToUnmark);
    }

    /**
     * Unmarks a Task from being done.
     * If it is already unmarked, this Command will have no effect.
     *
     * @param toDoList       the user's List of Tasks
     * @param numberToUnmark the item number to unmark
     * @throws DukeException when the item number to unmark does not exist
     */
    public void runCommand(TaskList toDoList, Integer numberToUnmark) throws DukeException {
        try {
            // Set the task as being unmarked
            Task taskToUnmark = toDoList.get(numberToUnmark);
            taskToUnmark.unmark();

            // Print out the formatted message after unmarking
            Ui.setDukeResponse(Parser.formatMsg("OK, I've marked this task as not done yet:\n" + taskToUnmark));
        } catch (IndexOutOfBoundsException e) {
            Ui.setDukeResponseError(Parser.formatMsg("OOPS!!! Item to unmark does not exist."));
            throw new DukeException(Parser.formatMsg("OOPS!!! Item to unmark does not exist."));
        }
    }
}
