package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class HelpCommand extends Command {

    private String[] tokens;

    public HelpCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public String execute(TaskList tl, Storage sge) {
        return "";
    }
}
