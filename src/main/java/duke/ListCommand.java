package duke;

public class ListCommand extends Command {

    ListCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        return ui.echo(this.taskList.getList());
    }
}
