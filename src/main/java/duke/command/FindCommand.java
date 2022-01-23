package duke.command;

import duke.operations.TaskList;

public class FindCommand extends Command{

    public FindCommand(String keyword) {
        super(null, null, keyword);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.findTaskInList(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
