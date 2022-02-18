package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.TaskList;

/**
 * Superclass for all command classes.
 */
public abstract class Command {

    protected static TaskList taskList;

    /**
     * Initialises {@code TaskList} used for all commands
     *
     * @param taskList To initialise with.
     */
    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    /**
     * Executes corresponding actions for each command.
     * Interacts with taskList.
     *
     * @return Message for successful execution.
     * @throws ApolloOutOfBoundsException If index supplied is out of bounds.
     */
    public abstract String execute() throws ApolloOutOfBoundsException;
}
