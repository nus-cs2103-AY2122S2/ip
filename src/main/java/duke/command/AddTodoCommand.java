package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Adds a Todo to the Task List.
 */
public class AddTodoCommand extends Command {
    private String description;
    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n"
            + "Let's try again ~(^.^)~\n"
            + "Type 'help' if you need to know how to use this command";

    /**
     * Constructor method for adding todos.
     *
     * @param input Description of the todo task.
     */
    public AddTodoCommand(String input) {
        assert input != null : "No description was provided to the task";
        description = input;
    }

    /**
     * Executes the addition of todos into the task list.
     *
     * @param tasks   TaskList that is maintained in Ducky.
     * @param ui      Ui that is maintained in Ducky.
     * @param storage Storage that is maintained in Ducky.
     * @throws DukeException Thrown when there is no description provided to the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        boolean isEmptyDescription = description.length() == 0;
        if (isEmptyDescription) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            Todo entry = new Todo(description);
            tasks.addTask(entry);
            String message = entry.getTask();
            return ("I have added the following todo:\n" + message
                    + "\nYou now have " + tasks.getSize() + " tasks");
        }
    }
}
