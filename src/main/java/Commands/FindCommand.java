package Commands;

import Exceptions.DukeException;
import Tasks.TaskList;
import Tasks.Task;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends DukeCommand {
    public FindCommand(String description) {
        super(description);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        TaskList arrListResult = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(this.commandBody)) {
                arrListResult.add(task);
            }
        }

        if (arrListResult.getSize() == 0 ){
            return ui.showEmptyFind();
        } else {
            return ui.showTaskList(arrListResult);
        }
    }
}
