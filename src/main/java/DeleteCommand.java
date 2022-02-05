public class DeleteCommand extends Command {
    public int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showDelete(tasks.get(number - 1), tasks);
        tasks.delete(number);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
