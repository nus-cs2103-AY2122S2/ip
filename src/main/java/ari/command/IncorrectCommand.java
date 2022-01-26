package main.java.ari.command;

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
