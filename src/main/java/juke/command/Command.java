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
     * Mapping for the first argument in a command.
     */
    protected static final String DEFAULT_PARAMETER = "";

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
    public boolean hasParameter(String param) {
        return paramArgs.containsKey(param);
    }

    /**
     * Checks if the command has an argument to a given parameter.
     * Returns true if it exists, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    public boolean hasArgument(String param) {
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
    public String getArgument(String param) {
        if (hasParameter(param)) {
            return paramArgs.get(param).orElseGet(() -> {
                result = Result.error(new JukeInvalidParameterException(param));
                return "";
            });
        } else {
            result = Result.error(new JukeInvalidParameterException(param));
            return "";
        }
    }

    /**
     * Checks if the given parameter is the default parameter.
     * Returns true if it is, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    public boolean isDefaultParameter(String param) {
        return param.equals(DEFAULT_PARAMETER);
    }

    /**
     * Checks if the default argument exists.
     * Returns true if it exists, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean hasDefaultArgument() {
        return hasArgument(DEFAULT_PARAMETER);
    }

    /**
     * Returns the default argument.
     * Updates the result to an error if it does not exist and returns an empty string.
     *
     * @return Default argument.
     */
    public String getDefaultArgument() {
        return paramArgs.get(DEFAULT_PARAMETER).orElseGet(() -> {
            result = Result.error(new JukeException("Missing default argument."));
            return "";
        });
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
        if (args != null && !args.isBlank()) {
            option = Optional.of(args);
        }
        if (paramArgs.containsKey(param)) {
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
        if (param == null || param.isBlank()) {
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
