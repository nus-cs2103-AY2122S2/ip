package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.TaskList;

public abstract class Command {

    protected static TaskList taskList;

    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    public abstract String execute() throws ApolloOutOfBoundsException;
}
