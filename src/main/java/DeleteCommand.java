import java.util.ArrayList;

public class DeleteCommand extends Command {

    private int id;

    public DeleteCommand(String i) {
        this.id = Integer.parseInt(i);;
    }

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        Task removed_task = tasks.delete_task(this.id);
        ui.notifyRemovedTaskMessage(removed_task);
        storage.saveToFile(tasks.get_tasks());
    }
}
