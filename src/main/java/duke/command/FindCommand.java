package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    private final String findString;

    /**
     * Finds all the tasks that contains the keyword that was provided.
     *
     * @param findString the keyword that the user wants to find
     */
    public FindCommand(String findString) {
        this.findString = findString;
    }

    /**
     * Execute.
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
            List<Task> filteredTask = tasks.getTasks().stream()
                    .filter(task -> task.getTaskName().contains(findString))
                    .collect(Collectors.toList());

            if (filteredTask.isEmpty()) {
                ui.printWithDivider(String.format("I could not find any tasks that contains \"%s\"", findString));
            } else {
                StringBuilder sb = new StringBuilder(ui.paddingString + "You've searched for: \""
                        + findString + "\"\n");
                sb.append(ui.paddingString + "Here are the matching tasks in your list:");

                int[] taskCount = {1};
                filteredTask.forEach(task -> {
                    sb.append(String.format("\n" + ui.paddingString + "  %d. " + task, taskCount[0]++));
                });
                ui.printWithDivider(sb);
            }
        }
    }
}
