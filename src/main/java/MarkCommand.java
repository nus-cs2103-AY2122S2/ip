public class MarkCommand extends Command {
    public int number;

    public MarkCommand(int number) {
        this.number = number;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(number);
        ui.showMark(tasks.get(number));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
