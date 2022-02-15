package duke;

/**
 * Command to handle keyword tag
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class TagCommand extends Command {

    TagCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Tags the given task
     *
     * @return Details of the tagged task
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 3);
        int index = Integer.parseInt(detailArray[1]) - 1;
        String tagDetail = "#" + detailArray[2];
        if (index < this.taskList.getListSize()) {
            return ui.echo("The following task is tagged" + this.taskList.tagTask(index, tagDetail));
        } else {
            return ui.echo("Index out of range");
        }
    }
}
