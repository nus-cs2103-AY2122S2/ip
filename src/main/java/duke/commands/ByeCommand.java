package duke.commands;

import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public String execute(TaskList taskList, Storage storage) {
        System.exit(0);;
        return ui.showExitMessage();
    }

}
