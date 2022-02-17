package duke;

/**
 * Represents a command to sort the tasks in the task list by alphabetical order
 */
public class SortCommand extends Command {
    SortCommand(String command) {
        super(command);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.sort();
        return taskList.list();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
