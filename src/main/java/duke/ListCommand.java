package duke;

/**
 * Command to handle keyword list
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class ListCommand extends Command {

    ListCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Returns the whole taskList
     *
     * @return String contains all the tasks
     */
    @Override
    String runCommand() {
        return ui.echo(this.taskList.getList());
    }
}
