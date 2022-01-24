import java.io.IOException;

public class ByeCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        ui.endChat();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
