import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage);
}
