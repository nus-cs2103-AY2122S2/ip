package duke.command;

public class ListCommand extends Command {

    public ListCommand();

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

}