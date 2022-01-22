package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private final String task;

    public FindCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String statement = "Here are the matching tasks in your list:\n";
        System.out.println(statement + taskList.findMatching(task));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
