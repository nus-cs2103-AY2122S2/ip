public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskIndex);
        
        ui.showMessage("Noted. I have deleted this task:");
        ui.showMessage(deletedTask.toString());
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
