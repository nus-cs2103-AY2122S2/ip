package duke.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to capture fields and encapsulate behavior of a Command
 * A Command is parsed from user input
 */
public class Command {
    /** Name of Command */
    private String name;

    /** Argument of Command */
    private String argument;

    /** Keyword arguments */
    private Map<String, String> kwargs;

    /**
     * Creates a Command object
     *
     * @param name name of command
     */
    public Command(String name) {
        this(name, "", new HashMap<>());
    }

    /**
     * Creates a Commmand object with arg
     *
     * @param name name of command
     * @param arg argument of command
     */
    public Command(String name, String arg) {
        this(name, arg, new HashMap<>());
    }

    /**
     * Creates a Command object with arg and kwargs
     *
     * @param name name of command
     * @param arg argument of command
     * @param kwargs keyword arguments of command
     */
    public Command(String name, String arg, Map<String, String> kwargs) {
        this.name = name;
        this.argument = arg;
        this.kwargs = kwargs;
    }

    /**
     * Getter for name
     *
     * @return name of command
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for argument
     *
     * @return argument of command
     */
    public String getArgs() {
        return this.argument;
    }

    /**
     * Getter for keyword arguments
     *
     * @return keyword arguments of command
     */
    public Map<String, String> getKwargs() {
        return this.kwargs;
    }
}
