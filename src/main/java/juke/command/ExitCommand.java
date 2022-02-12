package juke.command;

import juke.exception.JukeException;
import juke.exception.JukeInvalidParameterException;

/**
 * Command to exit Juke.
 */
public class ExitCommand extends Command {
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
     * Exits the main running loop of the application.
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
        this.juke.exit();
        this.result = Result.success("Until we meet again!");
        return this;
    }
}
