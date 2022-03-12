package juke.command;

import juke.exception.JukeEmptyTaskListException;
import juke.exception.JukeException;
import juke.exception.JukeInvalidParameterException;

/**
 * Command to list all the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Checks if the parameters and arguments are valid.
     * Does not require any arguments.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (hasDefaultArgument()) {
            setErroneousResult(new JukeException("Default argument not needed."));
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
     * Lists the tasks in the task list.
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        if (isSuccessful()) {
            return this;
        }
        checkParametersAndArguments();
        if (isErroneous()) {
            return this;
        }
        assert isEmpty();
        try {
            String[] strs = juke.getTaskList().list();
            setSuccessfulResult(strs);
        } catch (JukeEmptyTaskListException e) {
            setErroneousResult(e);
            return this;
        }
        return this;
    }
}
