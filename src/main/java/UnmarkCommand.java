public class UnmarkCommand extends Command {
    public int number;

    public UnmarkCommand(int number) {
        this.number = number;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(number);
        ui.showUnmark(tasks.get(number));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
