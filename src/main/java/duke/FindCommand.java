package duke;

public class FindCommand extends Command {
    FindCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.findTask(super.getCommand(), taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
