package duke.command;

public abstract class Command {
    public abstract String executeCommand();

    public boolean isExit() {
        return false;
    }
}
