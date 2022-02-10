package duke.command;

import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList) {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
