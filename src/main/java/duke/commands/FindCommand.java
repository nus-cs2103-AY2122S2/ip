package duke.commands;

/**
 * Lists all tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute() {
        return super.taskList.findTasks(this.keyword);
    }
}
