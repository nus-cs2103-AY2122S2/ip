package duke;

public class ExitCommand extends Command {

    /**
     * Executes the instance of Exit Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.sayGoodbye();
    }

    /**
     * Checks whether this command is the terminating command to Duke.
     *
     * @return True.
     */
    @Override
    public boolean isEnd() {
        return true;
    }
}
