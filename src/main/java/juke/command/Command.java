package juke.command;

import java.util.HashMap;
import java.util.Optional;

import juke.Juke;
import juke.exception.JukeException;
import juke.exception.JukeInvalidParameterException;

/**
 * Abstraction for a command.
 */
public abstract class Command {
    /**
     * String key for the first argument in a command.
     */
    public static final String DEFAULT_PARAMETER = "";

    /**
     * Message for the missing default argument exception.
     */
    private static final String MISSING_DEFAULT_ARGUMENT_MESSAGE = "Missing default argument.";

    /**
     * Reference to the Juke instance.
     */
    protected final Juke juke = Juke.getInstance();

    /**
     * Mapping of command arguments to its parameters.
     */
    protected HashMap<String, Optional<String>> paramArgs = new HashMap<>();

    /**
     * Result of the command execution.
     */
    protected Result result = Result.empty();

    /**
     * Constructor that initializes the command.
     */
    public Command() {
        paramArgs.put("", Optional.empty());
    }

    /**
     * Checks if the parameters and arguments are valid.
     *
     * @return This command.
     */
    public abstract Command checkParametersAndArguments();

    /**
     * Tries to execute the command, updating the result.
     *
     * @return This command.
     */
    public abstract Command execute();

    /**
     * Checks if the command has the given parameter.
     * Returns true if it exists, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    protected boolean hasParameter(String param) {
        return paramArgs.containsKey(param);
    }

    /**
     * Checks if the command has an argument to a given parameter.
     * Returns true if it exists, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    protected boolean hasArgument(String param) {
        if (!paramArgs.containsKey(param)) {
            return false;
        }
        return paramArgs.get(param).isPresent();
    }

    /**
     * Returns the argument associated with a given parameter.
     * Updates the result to an error if the argument or parameter is missing and returns an empty string.
     *
     * @param param Given parameter.
     * @return Argument.
     */
    protected String getArgument(String param) {
        boolean isMissingArgumentOrParameter = !hasParameter(param) || !hasArgument(param);
        if (isMissingArgumentOrParameter) {
            setErroneousResult(new JukeInvalidParameterException(param));
            return "";
        }
        assert paramArgs.get(param) != null;
        return paramArgs.get(param).orElse("");
    }

    /**
     * Checks if the given parameter is the default parameter.
     * Returns true if it is, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    protected boolean isDefaultParameter(String param) {
        return param.equals(DEFAULT_PARAMETER);
    }

    /**
     * Checks if the default argument exists.
     * Returns true if it exists, false otherwise.
     *
     * @return Boolean result.
     */
    protected boolean hasDefaultArgument() {
        return hasArgument(DEFAULT_PARAMETER);
    }

    /**
     * Returns the default argument.
     * Updates the result to an error if it does not exist and returns an empty string.
     *
     * @return Default argument.
     */
    protected String getDefaultArgument() {
        return paramArgs.get(DEFAULT_PARAMETER).orElseGet(() -> {
            setErroneousResult(new JukeException(MISSING_DEFAULT_ARGUMENT_MESSAGE));
            return "";
        });
    }

    /**
     * Sets the result to an error with a given exception.
     *
     * @param e Exception.
     */
    protected void setErroneousResult(Exception e) {
        result = Result.error(e);
    }

    /**
     * Sets the result to an success with a given message or messages.
     *
     * @param messages Messages.
     */
    protected void setSuccessfulResult(String... messages) {
        result = Result.success(messages);
    }

    /**
     * Adds or replaces a parameter with optional argument into the command.
     *
     * @param param Parameter.
     * @param args Argument or empty string.
     * @return This command.
     */
    public Command addParameter(String param, String args) {
        Optional<String> option = Optional.empty();
        boolean hasArgument = args != null && !args.isBlank();
        if (hasArgument) {
            option = Optional.of(args);
        }
        if (hasParameter(param)) {
            paramArgs.replace(param, option);
        } else {
            paramArgs.put(param, option);
        }
        return this;
    }

    /**
     * Removes a parameter and argument from the command.
     *
     * @param param Parameter.
     * @return This command.
     */
    public Command removeParameter(String param) {
        boolean isDefaultParameter = param == null || param.isBlank();
        if (isDefaultParameter) {
            paramArgs.replace("", Optional.empty());
        } else {
            paramArgs.remove(param);
        }
        return this;
    }

    /**
     * Returns the result of this command.
     *
     * @return Result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Checks if the result is a success.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isSuccessful() {
        return result instanceof Result.Success;
    }

    /**
     * Checks if the result is an error.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isErroneous() {
        return result instanceof Result.Error;
    }

    /**
     * Checks if the result is empty.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isEmpty() {
        return result instanceof Result.Empty;
    }
}
