import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String description, LocalDate by, String time) {
        this.deadline = new Deadline(description, by, time);
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(deadline);
        ui.showDeadline(this.deadline, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
