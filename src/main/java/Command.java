import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException;
    public abstract boolean isExit();
}
