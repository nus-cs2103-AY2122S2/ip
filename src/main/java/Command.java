import java.io.IOException;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return true;
    }
}
