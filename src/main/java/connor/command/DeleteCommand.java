package connor.command;

import connor.task.TaskList;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void activate() {
        TaskList.deleteTask(index);
    }
}
