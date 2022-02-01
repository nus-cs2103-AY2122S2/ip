package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.stream.Stream;

public class ListCommand extends Command {
    /**
     * Prints out the content of `TaskList` with its respective entry numbers.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printWithDivider("Your list is empty!");
        } else {
            StringBuilder sb = new StringBuilder(ui.STR_PADDING + "Here are the tasks in your list: \n");
            int[] taskCount = {1};

            tasks.getTasks().forEach( task -> {
                sb.append(String.format(ui.STR_PADDING + "  %d. " + task, taskCount[0]++));

                if (taskCount[0] != tasks.size() + 1) {
                    sb.append("\n");
                }
            });
            ui.printWithDivider(sb);
        }
    }

    /**
     * Returns `true` if the `Command` is `ExitCommand`.
     *
     * @return `true` if the `Command` is `ExitCommand`
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
