package juke.command;

import java.util.List;

import juke.exception.JukeEmptyTaskListException;
import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;
import juke.task.Task;

/**
 * Command to find tasks matching a string.
 */
public class FindCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Found task(s):";
    private static final String COMMAND_NAME = "find";

    /**
     * Descriptions of tasks.
     */
    private String[] descriptions;

    /**
     * Checks if the parameters and arguments are valid.
     * Requires a string to query.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!this.hasDefaultArgument()) {
            setErroneousResult(new JukeMissingArgumentException(COMMAND_NAME));
            return this;
        }
        return this;
    }

    /**
     * Returns if there are unnecessary parameters.
     *
     * @return Boolean result.
     */
    private boolean hasUnnecessaryParameters() {
        for (String param : paramArgs.keySet()) {
            if (isDefaultParameter(param)) {
                continue;
            }
            setErroneousResult(new JukeInvalidParameterException(param));
            return true;
        }
        return false;
    }

    /**
     * Tries to execute the command, updating the result.
     * Finds the tasks with a matching string as the query.
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        if (this.isSuccessful()) {
            return this;
        }
        this.checkParametersAndArguments();
        if (this.isErroneous()) {
            return this;
        }
        assert result instanceof Result.Empty;
        descriptions = null;
        if (!canFindTask()) {
            return this;
        }
        assert descriptions != null;
        setSuccessfulResult(descriptions);
        return this;
    }

    /**
     * Finds the tasks with a matching string as the query.
     * Returns if it is possible to find a task.
     *
     * @return Boolean result.
     */
    private boolean canFindTask() {
        List<Task> tasks = juke.getTaskList().search(getDefaultArgument());
        if (tasks.size() == 0) {
            setErroneousResult(new JukeEmptyTaskListException());
            return false;
        }
        descriptions = new String[tasks.size() + 1];
        descriptions[0] = SUCCESS_MESSAGE;
        for (int i = 0; i < tasks.size(); i++) {
            descriptions[i + 1] = tasks.get(i).toString();
        }
        return true;
    }
}
