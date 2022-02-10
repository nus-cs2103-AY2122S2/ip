package duke.command;

public class ExitCommand extends Command {
    @Override
    public String executeCommand() {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
