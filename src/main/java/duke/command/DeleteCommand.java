package duke.command;

import duke.task.Task;
import duke.operations.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(Task task) {
        super(task, null,null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteFromList(super.task);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
