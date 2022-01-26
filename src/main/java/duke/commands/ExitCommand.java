package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui io, Storage storage) {
        io.bye();
        exitProgram = true;
    }
}
