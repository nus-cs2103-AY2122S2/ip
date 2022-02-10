package jarvis.commands;

import jarvis.exceptions.InvalidTaskException;
import jarvis.exceptions.JarvisException;
import jarvis.tasks.TaskList;
import jarvis.utils.TextUI;

public abstract class Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Function to execute the command.
     *
     * @param taskList taskList object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    public void execute(TaskList taskList, TextUI ui) throws JarvisException {
        ui.printMsg(getResult(taskList));
    }

    /**
     * Function to execute the command and get the result.
     * @param taskList taskList object
     * @throws InvalidTaskException invalid task
     */
    public abstract String getResult(TaskList taskList) throws JarvisException;
}
