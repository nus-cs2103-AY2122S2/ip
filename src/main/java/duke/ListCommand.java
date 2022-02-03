package duke;

/**
 * Command to show all list items in tasklist
 */
class ListCommand extends Command {

    ListCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
