public class DeleteCommand extends Command {
    public int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.delete(number);
        ui.showDelete(tasks.get(number), tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
