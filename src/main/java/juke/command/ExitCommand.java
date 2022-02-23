package juke.command;

import juke.exception.JukeException;
import juke.exception.JukeInvalidParameterException;

/**
 * Command to exit Juke.
 */
public class ExitCommand extends Command {
    public static final String SUCCESS_MESSAGE = "Until we meet again!";

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
        if (this.hasDefaultArgument()) {
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
     * Exits the main running loop of the application.
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
        setSuccessfulResult(SUCCESS_MESSAGE);
        juke.exit();
        return this;
    }
}
