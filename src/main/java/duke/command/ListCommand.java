package duke.command;

import duke.task.Task;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage)  {
        if (taskList.tasksArrayList.size() == 0) {
            ui.noTaskLeft();
        } else {
            for (int i = 0; i < taskList.tasksArrayList.size(); i ++) {
                Task task = taskList.tasksArrayList.get(i);
                ui.listed(i + 1, task);
            }
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return obj instanceof ListCommand;
        } else {
            return false;
        }
    }
}
