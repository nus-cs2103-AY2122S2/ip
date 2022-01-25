import java.io.IOException;

public abstract class Command {
    boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException;
}
