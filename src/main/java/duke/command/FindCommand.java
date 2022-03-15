package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList taskList = tasks.findTasks(keyword);
        return taskList.isEmpty() ? Ui.showFindNoTasks(keyword) : Ui.showFindTasks(taskList);
    }
}
