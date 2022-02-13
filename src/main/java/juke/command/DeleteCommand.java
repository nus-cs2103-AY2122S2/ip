package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.Task;

/**
 * Command for deleting tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to delete.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        for (String param : paramArgs.keySet()) {
            if (!isDefaultParameter(param)) {
                result = Result.error(new JukeInvalidParameterException(param));
                return this;
            }
        }
        if (!hasDefaultArgument()) {
            result = Result.error(new JukeMissingArgumentException("delete"));
            return this;
        }
        return this;
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
        try {
            int index = Integer.parseInt(getDefaultArgument()) - 1;
            if (index < 0 || index >= juke.getTaskList().size()) {
                this.result = Result.error(new JukeInvalidTaskIndexException());
                return this;
            }
            Task task = juke.getTaskList().remove(index);
            result = Result.success(String.format("Successfully deleted task: %s.", task.getDescription()));
        } catch (NumberFormatException e) {
            result = Result.error(e);
            return this;
        }
        juke.getStorage().saveTasks();
        return this;
    }
}
