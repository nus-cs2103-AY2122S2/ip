package Duke.Commands;

import Duke.Managers.TaskList;
import Duke.Managers.Ui;
import Duke.Managers.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui io, Storage storage) {
        io.bye();
        exit = true;
    }
}
