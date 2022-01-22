package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

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
            Task taskToUnmark = toDoList.get(numberToUnmark);
            taskToUnmark.unmark();
            System.out.println(Parser.formatMsg("OK, I've marked this task as not done yet:\n\t" + taskToUnmark));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! Item to unmark does not exist."));
        }
    }
}
