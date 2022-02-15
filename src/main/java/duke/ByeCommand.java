package duke;

/**
 * The command to handle keyword bye
 */
public class ByeCommand extends Command {

    ByeCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * saves all tasks and says goodbye to user
     *
     * @return Bye message to users
     */
    @Override
    String runCommand() {
        this.storage.save(this.taskList.getArrayList());
        return ui.sayBye();
    }

}
