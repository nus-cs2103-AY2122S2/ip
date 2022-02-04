public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        userInt.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
