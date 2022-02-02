package spike.command;

import spike.task.TaskList;

/**
 * Handles data before exiting.
 */
public class ExitCommand extends Command {
    public static final String EXIT_MESSAGE = "See you soon! ﾍ(=￣∇￣)ﾉ You may close this window now.";
    /**
     * Save changes to list to the hard disk.
     *
     * @param tasks current task list
     * @return result of attempt to save changes
     */
    @Override
    public String execute(TaskList tasks) {
        return EXIT_MESSAGE;
    }
}
