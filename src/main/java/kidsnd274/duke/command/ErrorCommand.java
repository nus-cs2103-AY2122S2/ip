package kidsnd274.duke.command;

public class ErrorCommand extends Command {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult runCommand() {
        return new CommandResult(message);
    }
}
