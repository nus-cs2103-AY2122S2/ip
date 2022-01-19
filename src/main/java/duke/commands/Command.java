package duke.commands;
import duke.main.*;

public abstract class Command<E> {
    public abstract void runCommand(TaskList todoList, E cmd);
}
