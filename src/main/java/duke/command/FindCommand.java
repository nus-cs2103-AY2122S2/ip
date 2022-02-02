package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class FindCommand extends Command {

    private String str;

    public FindCommand(String str) {
        super();
        this.str = str;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(str);
        ui.showMessage("Here are your matching tasks:");
        ui.showTasks(matchingTasks);
    }

}