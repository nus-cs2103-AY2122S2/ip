package spike.command;

import spike.task.TaskList;

/**
 * Warns user about incorrect command.
 */
public class IncorrectCommand extends Command {
    private String errorMsg;

    /**
     * Constructor using error message.
     *
     * @param errorMsg error message to display to user
     * @return a command to display error message
     */
    public IncorrectCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Returns the error message.
     *
     * @param tasks current task list
     * @return warning message
     */
    @Override
    public String execute(TaskList tasks) {
        return errorMsg;
    }

}
