public class AddEventTaskCommand extends Command {
    private final String description;
    private final String at;
    
    public AddEventTaskCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addEventTask(description, at);

        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
