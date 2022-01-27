import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    Deadline deadline;

    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.deadline = new Deadline(description, dateTime);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.deadline);
        ui.showAdd(deadline, taskList);
        storage.updateList(taskList);
    }

}
