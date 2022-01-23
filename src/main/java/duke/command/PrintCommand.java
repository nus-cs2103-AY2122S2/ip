package duke.command;

import duke.functionality.TaskList;

public class PrintCommand extends Command{

    public PrintCommand() {
        super(null, null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
