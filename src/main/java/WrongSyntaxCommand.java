import java.io.IOException;

public class WrongSyntaxCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        BotException exception = new BotException();
        exception.wrongSyntax();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
