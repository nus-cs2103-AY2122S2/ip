package connor.command;

import connor.task.SortType;
import connor.task.TaskList;

/**
 * Represents a Sort {@code Command}.
 */
public class SortCommand extends Command {
    private SortType sortType;

    public SortCommand(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public String activate() {
        return TaskList.sortTasks(sortType);
    }

}
