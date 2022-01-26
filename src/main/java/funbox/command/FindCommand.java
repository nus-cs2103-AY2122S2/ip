package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class FindCommand extends Command {
    String description;
    public FindCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTasks(this.description, ui, taskList);
    }
}
