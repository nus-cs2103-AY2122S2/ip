import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends AddCommand {
    public LocalDate date;
    public LocalTime start;
    public LocalTime end;
    public EventCommand(String taskName, LocalDate date, LocalTime start, LocalTime end) {
        super(taskName);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task task = new Event(getTaskName(), this.date, this.start, this.end);
        addAndUpdate(task, tasks, ui, store);
    }
}
