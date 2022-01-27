package command;

import task.TaskList;
import utility.Input;

public class ExitCommand extends Command{
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Input input) {
        input.print("Aww. Bye! See you again soon");
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
