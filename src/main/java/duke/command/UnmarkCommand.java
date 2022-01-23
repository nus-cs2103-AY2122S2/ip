package duke.command;

import duke.operations.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(Integer num) {
        super(null, num);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.unmark(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
