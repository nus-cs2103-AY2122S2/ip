import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
