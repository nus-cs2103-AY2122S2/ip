package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printWithDivider("Your list is empty!");
        } else {
            StringBuilder sb = new StringBuilder(ui.STR_PADDING + "Here are the tasks in your list: \n");

            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                sb.append(String.format(ui.STR_PADDING + "  %d. " + currentTask.toString(), i + 1));

                if (i != tasks.size() - 1) {
                    sb.append("\n");
                }
            }
            ui.printWithDivider(sb);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
