package luke.commands;

import luke.data.tasks.Todo;

/**
 * Implements the todo command.
 */
public class TodoCommand extends AddCommand {

    /**
     * Constructs the todo command with the specified todo task.
     * @param todo The specified todo task to be added.
     */
    public TodoCommand(Todo todo) {
        super(todo);
    }
}
