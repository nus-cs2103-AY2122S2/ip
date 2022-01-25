public class SetupCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.populateWith(storage.loadTasksFromFile());
        ui.greet();
    }
}
