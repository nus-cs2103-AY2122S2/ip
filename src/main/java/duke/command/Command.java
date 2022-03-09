package duke.command;
import duke.TasksList;
import duke.Storage;

public abstract class Command {
    public abstract String execute(TasksList taskList, Storage storage);
}
