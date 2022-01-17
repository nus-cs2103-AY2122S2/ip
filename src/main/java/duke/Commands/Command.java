package duke.Commands;
import duke.main.*;

public abstract class Command {
    public abstract void runCommand(TaskList todoList, String cmd);
}
