import java.time.LocalDate;

public class AddDeadlineTaskCommand extends Command {
    private final String description;
    private final LocalDate date;

    public AddDeadlineTaskCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addDeadlineTask(description, date);

        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
