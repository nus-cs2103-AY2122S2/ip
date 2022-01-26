package duke.commands;

public class ByeCommand extends  Command<String> {
    public ByeCommand() {
        execute();
    }

    private void execute() {
        isExit();
    }

    public boolean isExit() {
        return true;
    }
}
