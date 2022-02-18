package duke.commands;

/**
 * Lists all tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @return response from the find command.
     */
    public String execute() {
        String findResult = super.taskList.findTasks(this.keyword);
        return findResult;
    }
}
