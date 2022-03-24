import java.io.IOException;

public abstract class Command {
    String commandArgument;
    private final boolean isExit;

    Command(String commandArgument) {
        this.commandArgument = commandArgument;
        this.isExit = false;
    }

    Command(String commandArgument, boolean isExit) {
        this.commandArgument = commandArgument;
        this.isExit = isExit;
    }

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws IOException;

    public boolean isExit() {
        return isExit;
    }
}
