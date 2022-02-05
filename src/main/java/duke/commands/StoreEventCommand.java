package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;


/**
 * Represents an event command recognized by the parser.
 * Upon execution, it will attempt to store an event into
 * the task list.
 */
public class StoreEventCommand extends StoreCommand {

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("event");
    }

    /**
     * Executes the StoreEventCommand object.
     *
     * @param taskList a container of existing tasks in the program, used for the creation
     *                 and addition of an event task into the task list.
     * @return a notification about the added event task.
     * @throws DukeException when the user input provided does not satisfy the format
     *                       to create an event task.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "Got it. I've added this task:\n    "
                + taskList.addEventTask(tokens)
                + "\nNow you have " + taskList.getSize() + " task(s) in the list.";
    }
}
