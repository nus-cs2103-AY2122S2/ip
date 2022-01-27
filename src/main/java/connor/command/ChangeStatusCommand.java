package connor.command;

import connor.task.TaskStatus;
import connor.task.TaskList;

public class ChangeStatusCommand extends Command {
    private TaskStatus ts;
    private int index;

    public ChangeStatusCommand(TaskStatus ts, int index) {
        this.ts = ts;
        this.index = index;
    }

    @Override
    public void activate() {
        TaskList.markStatus(ts, index);
    }
}
