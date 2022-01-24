import java.io.IOException;
import java.util.ArrayList;

public class ShowListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        ArrayList<Task> storingList = taskList.showStoringList();
        ui.showListTask(storingList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
