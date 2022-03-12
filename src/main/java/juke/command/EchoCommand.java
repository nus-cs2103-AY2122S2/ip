package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;

/**
 * Command for echo.
 */
public class EchoCommand extends Command {
    private static final String COMMAND_NAME = "echo";

    /**
     * Checks if the parameters and arguments are valid.
     * Requires the string to echo.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!hasDefaultArgument()) {
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
     * Echos a string.
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
        setSuccessfulResult(getDefaultArgument());
        return this;
    }
}
