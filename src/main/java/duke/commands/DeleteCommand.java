package duke.commands;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;

/**
 * DeleteCommand is a Command.
 * This Command is used to Delete Tasks from the TaskList
 */
public class DeleteCommand extends Command<Integer> {
    /**
     * Constructor for DeleteCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList       the user's List of Tasks
     * @param numberToDelete the item number to delete
     * @throws DukeException when the item number to delete does not exist
     */
    public DeleteCommand(TaskList toDoList, Integer numberToDelete) throws DukeException {
        this.runCommand(toDoList, numberToDelete);
    }

    /**
     * Deletes a Task from the current TaskList
     *
     * @param toDoList       the user's List of Tasks
     * @param numberToDelete the item number to delete
     * @throws DukeException when the item number to delete does not exist
     */
    public void runCommand(TaskList toDoList, Integer numberToDelete) throws DukeException {
        try {
            // Remove the Task from the TaskList
            Task deletedTask = toDoList.remove(numberToDelete);

            // Print out the formatted message after removing from TaskList
            Ui.setDukeResponse(Parser.formatMsg("Noted. I've removed this task:\n" + deletedTask
                    + "\nNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            Ui.setDukeResponseError("☹ OOPS!!! Item to delete does not exist.");
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! Item to delete does not exist."));
        }
    }
}
