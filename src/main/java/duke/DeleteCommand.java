package duke;

/**
 * The command to handle keyword delete
 */
public class DeleteCommand extends Command {

    DeleteCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Deletes a task from taskList
     *
     * @return Details of the task deleted
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        int index = Integer.parseInt(detailArray[1]) - 1;
        if (index < this.taskList.getListSize()) {
            return ui.echo("Okay, I have remove this task:\n"
                    + this.taskList.deleteTask(index));
        } else {
            return ui.echo("Index out of range");
        }
    }
}
