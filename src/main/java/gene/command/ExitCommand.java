package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        userInt.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
