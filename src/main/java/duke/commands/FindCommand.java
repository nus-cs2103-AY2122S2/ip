package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> allTasks = taskList.getTasks();
            StringBuilder sb = new StringBuilder();
            int length = allTasks.size();

            if (length == 0) {
                ui.output("No tasks found based on keyword! Also, quit lazing around!");
                return;
            }

            sb.append("Here are the matching tasks in your list:\n");

            List<Task> filteredTasks =
                    allTasks
                    .stream()
                    .filter(task -> task.getDescription().contains(this.args))
                    .collect(Collectors.toList());
            int filteredLength = filteredTasks.size();

            if (filteredLength < 1) {
                ui.output("No tasks found based on keyword! Also, quit lazing around!");
                return;
            }

            for (int i = 0; i < filteredLength; ++i) {
                sb.append(i + 1 + ". " + filteredTasks.get(i).toString());

                if (i + 1 != length) {
                    sb.append("\n");
                }
            }

            ui.output(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
