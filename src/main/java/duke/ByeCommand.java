package duke;

public class ByeCommand extends Command {

    ByeCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        this.storage.save(this.taskList.getArrayList());
        return ui.sayBye();
    }

}
