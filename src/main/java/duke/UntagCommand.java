package duke;

/**
 * Command to handle keyword untag
 */
public class UntagCommand extends Command {

    UntagCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Removes all the tags of the given task
     *
     * @return Details of the given task
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 3);
        int index = Integer.parseInt(detailArray[1]) - 1;
        if (index < this.taskList.getListSize()) {
            return ui.echo("The following task is untagged" + this.taskList.untagTask(index));
        } else {
            return ui.echo("Index out of range");
        }
    }
}
