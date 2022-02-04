public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        userInt.print();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
