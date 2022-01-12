package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

import java.io.IOException;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        ui.printList(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
