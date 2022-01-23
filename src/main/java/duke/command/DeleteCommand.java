package duke.command;

import duke.functionality.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(Integer number) {

        super(null, number, null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(super.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
