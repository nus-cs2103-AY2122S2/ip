public class MarkTaskCommand extends Command {
    private final int taskIndex;
    
    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.markTask(taskIndex);

        ui.showMessage("Nice! I've marked this task as done: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
    }
}
