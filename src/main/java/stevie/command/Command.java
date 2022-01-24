package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui);
}
