package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.stream.Stream;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printWithDivider("Your list is empty!");
        } else {
            StringBuilder sb = new StringBuilder(ui.STR_PADDING + "Here are the tasks in your list: \n");
            int[] taskCount = {1};

            tasks.forEach(task -> {
                sb.append(String.format(ui.STR_PADDING + "  %d. " + task.toString(), taskCount[0]++));

                if (taskCount[0] != tasks.size() + 1) {
                    sb.append("\n");
                }
            });
            ui.printWithDivider(sb);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
