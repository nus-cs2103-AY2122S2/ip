public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.unmarkTask(taskIndex);
        
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
    }
}
