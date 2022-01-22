import java.io.IOException;

public class MarkCommand extends Command {
    protected int number;

    public MarkCommand(int number) {
        this.number = number - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(number);
        ui.showMarkTask(tasks.getTasks().get(number));
        storage.save(tasks.getTasks());
    }
}
