package luke.commands;

import luke.data.TaskList;

public abstract class Command {

    public boolean isExitCmd() {
        return false;
    }

    public abstract CommandResult execute(TaskList tasklist);
}
