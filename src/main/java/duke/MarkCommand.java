package duke;

/**
 * Command to handle keyword mark
 */
public class MarkCommand extends Command {

    MarkCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Marks the task as done
     *
     * @return Details of the task
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        int index = Integer.parseInt(detailArray[1]) - 1;
        if (index < this.taskList.getListSize()) {
            return ui.echo("You have finish: ! \n" + this.taskList.mark(index));
        } else {
            return ui.echo("Index out of range");
        }
    }
}
