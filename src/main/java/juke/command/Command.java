package juke.command;

import juke.Juke;
import juke.exception.JukeException;

import java.util.HashMap;
import java.util.Optional;

/**
 * Abstraction for a command.
 */
public abstract class Command {
    /**
     * Reference to the Juke instance.
     */
    protected final Juke juke = Juke.getInstance();
    
    /**
     * Mapping for the first argument in a command.
     */
    protected static final String DEFAULT_PARAMETER = "";
    
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
        this.paramArgs.put("", Optional.empty());
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
        return this.paramArgs.containsKey(param);
    }
    
    /**
     * Checks if the command has an argument to a given parameter.
     * Returns true if it exists, false otherwise.
     *
     * @param param Given parameter.
     * @return Boolean result.
     */
    public boolean hasArgument(String param) {
        //TODO Currently does not handle missing parameter case.
        return this.paramArgs.get(param).isPresent();
    }
    
    /**
     * Returns the argument associated with a given parameter.
     * Updates the result to an error if the argument or parameter is missing and returns an empty string.
     *
     * @param param Given parameter.
     * @return Argument.
     */
    public String getArgument(String param) {
        if (this.hasParameter(param)) {
            return this.paramArgs.get(param).orElseGet(() -> {
                this.result = Result.error(new JukeException("Missing argument for parameter " + param));
                return "";
            });
        } else {
            this.result = Result.error(new JukeException("Missing parameter " + param));
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
        return this.hasArgument(DEFAULT_PARAMETER);
    }
    
    /**
     * Returns the default argument.
     * Updates the result to an error if it does not exist and returns an empty string.
     *
     * @return Default argument.
     */
    public String getDefaultArgument() {
        return this.paramArgs.get(DEFAULT_PARAMETER).orElseGet(() -> {
            this.result = Result.error(new JukeException("Missing default argument"));
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
        // Short circuit evaluation right here
        if (args != null && !args.isBlank()) {
            option = Optional.of(args);
        }
        if (this.paramArgs.containsKey(param)) {
            this.paramArgs.replace(param, option);
        } else {
            this.paramArgs.put(param, option);
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
            this.paramArgs.replace("", Optional.empty());
        } else {
            this.paramArgs.remove(param);
        }
        return this;
    }
    
    /**
     * Returns the result of this command.
     *
     * @return Result.
     */
    public Result getResult() {
        return this.result;
    }
    
    /**
     * Checks if the result is a success.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isSuccessful() {
        return this.result instanceof Result.Success;
    }
    
    /**
     * Checks if the result is an error.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isErranous() {
        return this.result instanceof Result.Error;
    }
    
    /**
     * Checks if the result is empty.
     * Returns true if it is, false otherwise.
     *
     * @return Boolean result.
     */
    public boolean isEmpty() {
        return this.result instanceof Result.Empty;
    }
}
