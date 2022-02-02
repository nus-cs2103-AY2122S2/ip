public class CommandList extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks.tasksAsString());
    }
}
