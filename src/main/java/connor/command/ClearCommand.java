package connor.command;

import connor.task.TaskList;

public class ClearCommand extends Command {
    public ClearCommand() {}

    @Override
    public void activate() {
        TaskList.clearTasks();
    }
}
