package kenobi.command;

/**
 * The ListCommand class encapsulates the command to list a given TaskList.
 */
public class ListCommand extends Command {
    /**
     * Executes the listing of the TaskList.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        if (tasks.isEmpty()) {
            return "You don't have no tasks in the archives";
        }

        return "Here are all of your tasks in the archives\n" + tasks;
    }
}
