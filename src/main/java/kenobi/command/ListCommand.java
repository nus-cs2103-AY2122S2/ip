package kenobi.command;

/**
 * The ListCommand class encapsulates the command to list a given TaskList.
 */
public class ListCommand extends Command {
    private static final String EMPTY_TASK_LIST_MSG = "You have no tasks in the archives!";

    /**
     * Executes the listing of the TaskList.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        if (tasks.isEmpty()) {
            return EMPTY_TASK_LIST_MSG;
        }

        return "Here are all of your tasks in the archives\n" + tasks;
    }
}
