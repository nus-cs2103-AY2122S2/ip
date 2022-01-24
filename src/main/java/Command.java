import java.io.IOException;

public abstract class Command {

    public abstract void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException;

    public abstract boolean isExit();

}
