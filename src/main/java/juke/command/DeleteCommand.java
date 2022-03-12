package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.Task;

/**
 * Command for deleting tasks.
 */
public class DeleteCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Successfully deleted task: %s.";
    private static final String COMMAND_NAME = "delete";

    private static final String ALL_PARAMETER = "a";
    private static final String ALL_SUCCESS_MESSAGE = "Successfully deleted all tasks.";

    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to delete.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!hasDefaultArgument()
                && !hasParameter(ALL_PARAMETER)) {
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
            if (isDefaultParameter(param)
                    || (param.equals(ALL_PARAMETER) && !hasArgument(ALL_PARAMETER))) {
                continue;
            }
            setErroneousResult(new JukeInvalidParameterException(param));
            return true;
        }
        return false;
    }

    /**
     * Tries to execute the command, updating the result.
     * Deletes the given task from the task list.
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
            deleteTask();
        } catch (NumberFormatException e) {
            setErroneousResult(e);
            return this;
        } catch (IndexOutOfBoundsException e) {
            setErroneousResult(new JukeInvalidTaskIndexException());
            return this;
        }
        juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Delete the task at the given index.
     *
     * @throws NumberFormatException Throws if cannot parse to integer.
     */
    private void deleteTask() throws NumberFormatException {
        if (hasParameter(ALL_PARAMETER)) {
            assert !hasArgument(ALL_PARAMETER);
            juke.getTaskList().clear();
            setSuccessfulResult(ALL_SUCCESS_MESSAGE);
            return;
        }
        int index = Integer.parseInt(getDefaultArgument()) - 1;
        Task task = juke.getTaskList().remove(index);
        setSuccessfulResult(String.format(SUCCESS_MESSAGE, task.getDescription()));
    }
}
