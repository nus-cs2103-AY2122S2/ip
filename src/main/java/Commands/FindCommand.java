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

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        TaskList arrList = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(description)) {
                arrList.add(task);
            }
        }

        if (arrList.getSize() == 0 ){
            ui.showEmptyFind();
        } else {
            ui.printTaskList(arrList);
        }
    }
}
