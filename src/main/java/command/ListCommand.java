package command;

import task.TaskList;
import utility.UI;
import utility.Storage;

public class ListCommand extends Command{
    public ListCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList task, UI ui, Storage storage) {
        task.printTasks(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
