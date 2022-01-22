public class ExitCommand extends Command{
    public ExitCommand() {
        super(null, null);
    }

    @Override
    public void execute(TaskList tasks) {
        Ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
