public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
        storage.write(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
