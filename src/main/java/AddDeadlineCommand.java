import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//AddDeadLineCommand.java reused from Brigette Santoso E0564307
public class AddDeadlineCommand extends Command {
    protected String description;
    protected String deadline;
    protected LocalDate date;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    public AddDeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        Task task;
        if (date == null) {
            task = new Deadline(description, "D", deadline);
        } else {
            task = new Deadline(description, "D", date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}