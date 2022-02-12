package duke;

public class HiCommand extends Command {

    HiCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        return ui.greet();
    }
}
