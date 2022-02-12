package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;

/**
 * Command for echo.
 */
public class EchoCommand extends Command {
    /**
     * String to echo.
     */
    private String message;

    /**
     * Checks if the parameters and arguments are valid.
     * Requires the string to echo.
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
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeMissingArgumentException("echo"));
            return this;
        }
        return this;
    }

    /**
     * Tries to execute the command, updating the result.
     * Echos a string.
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
        this.message = this.getDefaultArgument();
        this.result = new Result.Success(this.message);
        return this;
    }
}
