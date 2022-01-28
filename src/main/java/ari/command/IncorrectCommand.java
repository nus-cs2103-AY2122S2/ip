package main.java.ari.command;

/**
 * Represents a command that has incorrect inputs
 */
public class IncorrectCommand extends Command {
    private String message;

    public IncorrectCommand(String displayMessage) {
        this.message = displayMessage;
    }

    @Override
    public String execute() {
        return this.message;
    }

}
