package kenobi.command;

/**
 * The UnmarkCommand class encapsulates the command to mark a Task in a given TaskList as undone.
 */
public class UnmarkCommand extends Command {
    private int toUnmarkIndex;

    /**
     * Constructs an UnmarkCommand with the given index.
     *
     * @param index The base-1 index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        toUnmarkIndex = index - 1;
    }

    /**
     * Executes the unmarking of the desired task.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        try {
            return "I guess you weren't done with this one:\n" + tasks.markTaskAsUndone(toUnmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }

}
