package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    private final String findString;

    public FindCommand(String findString) {
        this.findString = findString;
    }

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
                StringBuilder sb = new StringBuilder(ui.STR_PADDING + "You've searched for: \"" + findString + "\"\n");
                sb.append(ui.STR_PADDING + "Here are the matching tasks in your list:");

                int[] taskCount = {1};
                filteredTask.forEach(task -> {
                    sb.append(String.format("\n" + ui.STR_PADDING + "  %d. " + task, taskCount[0]++));
                });
                ui.printWithDivider(sb);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
