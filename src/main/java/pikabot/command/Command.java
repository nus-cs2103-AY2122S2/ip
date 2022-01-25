package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage);
}
