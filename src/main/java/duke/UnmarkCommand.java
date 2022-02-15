package duke;

/**
 * Command to handle keyword unmark
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class UnmarkCommand extends Command {

    UnmarkCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Unmarks the given task
     *
     * @return Details of the given task
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        int index = Integer.parseInt(detailArray[1]) - 1;
        if (index < this.taskList.getListSize()) {
            return ui.echo("Oh no! \n" + this.taskList.unMark(index));
        } else {
            return ui.echo("Index out of range");
        }
    }
}
