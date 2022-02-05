package duke.command;

public class CommandResult {
    private final String commandResult;

    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    public String toString() {
        return commandResult;
    }
}
