package kenobi.command;

/**
 * The MarkCommand class encapsulates the command to mark a Task in a given TaskList as done.
 */
public class MarkCommand extends Command {
    private int toMarkIndex;

    /**
     * Constructs a MarkCommand with the given index.
     *
     * @param index The base-1 index of the task to be marked.
     */
    public MarkCommand(int index) {
        toMarkIndex = index - 1;
    }

    /**
     * Executes the marking of the desired task.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        try {
            return "Here you go:\n" + tasks.markTaskAsDone(toMarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }
}
