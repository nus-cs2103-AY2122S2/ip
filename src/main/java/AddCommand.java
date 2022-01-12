import java.io.IOException;

public class AddCommand extends Command{
    protected Task obj;

    public AddCommand(Task obj) {
        this.obj = obj;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.add(obj);
        ui.addList(task);
        storage.appendFile(obj);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
