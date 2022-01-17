package Commands;
import main.*;

public abstract class Command {
    public abstract void runCommand(TaskList todoList, String cmd);
}
