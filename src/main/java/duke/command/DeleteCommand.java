package duke.command;

import duke.functionality.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(Integer number) {

        super(null, number);
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
