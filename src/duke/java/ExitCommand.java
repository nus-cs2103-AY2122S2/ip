package duke.java;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
