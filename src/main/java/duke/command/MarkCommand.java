package duke.command;

import duke.operations.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(Integer num) {
        super(null, num,null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.mark(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
