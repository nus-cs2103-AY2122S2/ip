package duke;

/**
 * Command to show all list items in tasklist
 */
class ListCommand extends Command {

    ListCommand(String command) {
        super(command);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.list(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
