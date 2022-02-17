package duke;

/**
 * Represents a command to find a specific task in the task list
 */
public class FindCommand extends Command {
    FindCommand(String command) {
        super(command);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.findTask(super.getCommand(), taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
