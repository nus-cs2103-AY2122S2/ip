package ari.command;

/**
 * Un-marks a Task
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private static final String UNMARK_MESSAGE = "Yes Master, I have marked this task as not done yet:\n    %s";

    private int indexToUnmark;

    public UnmarkCommand(int index) {
        this.indexToUnmark = index - 1;
    }

    @Override
    public String execute() {
        if (indexToUnmark < 0 || indexToUnmark >= taskList.getSize()) {
            return "Sorry Master, the item you chose is not in the list";
        }

        taskList.unmarkTask(indexToUnmark);
        return String.format(UNMARK_MESSAGE, taskList.getTask(indexToUnmark));
    }

}
