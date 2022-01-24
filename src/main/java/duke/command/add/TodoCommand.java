package duke.command.add;

import duke.task.Todo;

/**
 * Represents a command to add a todo task into the task list. A
 * <code>TodoCommand</code> object records the todo task input
 * by the user. When executing the object, the todo task being
 * stored will be added into the task list.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name>";

    /**
     * Creates an instance of an TodoCommand object.
     *
     * @param newTodo the new todo task being added to the task list.
     */
    public TodoCommand(Todo newTodo) {
        super(newTodo);
    }

    /**
     * Returns the command word of the TodoCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}

