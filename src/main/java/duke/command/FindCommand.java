package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        super("find");
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.list()) {
            if (task.toString().contains(input)) {
                result.add(task);
            }
        }

        if (result.size() != 0) {
            ui.listFindResults(result);
        } else {
            ui.taskNotFound();
        }
    }
}
