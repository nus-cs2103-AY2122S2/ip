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
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param)) {
                this.result = Result.error(new JukeInvalidParameterException(param));
                return this;
            }
        }
        if (this.hasDefaultArgument()) {
            this.result = Result.error(new JukeException("Default argument not needed."));
            return this;
        }
        return this;
    }

    /**
     * Tries to execute the command, updating the result.
     * Lists the tasks in the task list.
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
        try {
            String[] strs = this.juke.getTaskList().list();
            this.result = Result.success(strs);
        } catch (JukeEmptyTaskListException e) {
            this.result = Result.error(e);
            return this;
        }
        return this;
    }
}
