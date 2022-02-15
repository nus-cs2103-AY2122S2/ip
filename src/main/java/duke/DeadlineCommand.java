package duke;

/**
 * The command to handle keyword deadline
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class DeadlineCommand extends Command {

    DeadlineCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Creates a deadline and adds into the taskList
     *
     * @return Details of the new deadline and total number of task in taskList
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        String taskAndTime = detailArray[1];
        String[] arr = taskAndTime.split("/by");
        String task = arr[0];
        String time = arr[1];
        Task newTask = new Deadline(task, time);
        this.taskList.addToList(newTask);
        return ui.echo("Task added:" + newTask.toString() + "\n"
                + "Now you have " + this.taskList.getListSize() + " tasks in the list");
    }
}
