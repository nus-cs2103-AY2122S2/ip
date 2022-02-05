package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;


/**
 * Represents a todo command recognized by the parser.
 * Upon execution, it will attempt to store a todo into
 * the task list.
 */
public class StoreTodoCommand extends StoreCommand {

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("todo");
    }

    /**
     * Executes the StoreTodoCommand object.
     * Returns a String to notify the user that the task has been added.
     *
     * @param taskList a container of existing tasks in the program, used for the creation
     *                 and addition of a todo task into the task list.
     * @return a notification about the added todo task.
     * @throws DukeException when the user input provided does not satisfy the format
     *                       to create a todo task.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "Got it. I've added this task:\n    "
                + taskList.addTodoTask(tokens)
                + "\nNow you have " + taskList.getSize() + " task(s) in the list.";
    }
}
