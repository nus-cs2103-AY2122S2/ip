import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        ui.displayList(tasks.getCount(), tasks);
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
