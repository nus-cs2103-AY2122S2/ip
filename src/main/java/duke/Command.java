package duke;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler, IOException;
    public abstract boolean isExit();
}


