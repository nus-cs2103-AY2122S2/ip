package baron.commands;

/**
 * Represents an echo command that returns the string that was given.
 */
public class EchoCommand extends Command {
    private final String echoString;

    /**
     * Constructs an {@code EchoCommand} object with {code echoString} to be echoed.
     *
     * @param echoString string to be echoed.
     */
    public EchoCommand(String echoString) {
        this.echoString = echoString;
    }

    /**
     * Returns the string to be echoed that was set in this constructor.
     *
     * @return the string to be echoed that was set in this constructor.
     */
    @Override
    public String execute() {
        return this.echoString;
    }
}
