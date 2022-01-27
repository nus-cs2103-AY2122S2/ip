package command;

import task.TaskList;
import utility.Input;
import utility.Storage;

public class ListCommand extends Command{
    public ListCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList task, Input input, Storage storage) {
        task.printTasks(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
