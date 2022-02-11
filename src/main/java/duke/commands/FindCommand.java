package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        assert keyword != null;
        
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.find(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
