package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class FindCommand extends Command {

    private String[] tokens;
    public FindCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public String execute(TaskList tl, Storage sge) {
        return "Here's what I could find!\n" + tl.checkWordsInTask(this.tokens);
    }
}
