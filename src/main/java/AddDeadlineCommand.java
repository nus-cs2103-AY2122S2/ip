import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand extends Command {
    protected String description;
    protected String by;
    protected LocalDate date;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    public AddDeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (date == null) {
            task = new Deadline(description, by);
        }
        else {
            task = new Deadline(description, date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
