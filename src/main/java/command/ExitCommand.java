package command;

import task.TaskList;
import utility.Input;
import utility.Storage;

public class ExitCommand extends Command{
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Input input, Storage storage) {
        input.print("Aww. Bye! See you again soon");
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
