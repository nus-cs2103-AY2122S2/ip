import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable)
            throws IOException;
    public abstract boolean isExit();
}
