package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.findTasks(this.keyword));
    }

}
