package duke.command;

public class AddCommand extends Command {
    public Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task);
    }

}