import java.time.LocalDate;

public class AddCommand extends Command {
    String description;
    LocalDate date;

    public AddCommand(String keyword, String description, LocalDate date) {
        super(keyword);
        this.description = description;
        this.date = date ;
    }

    public AddCommand(String keyword, String description) {
        super(keyword);
        this.description = description;
        this.date = null;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = null;
        switch (keyword){
        case "todo":
            task = new Todo(description);
            break;
        case "event":
            task = new Event(description,date);
            break;
        case "deadline":
            task = new Deadline(description,date);
            break;
        }
        if (task != null) {
            tasks.add(task);
            storage.save(tasks.list());
            ui.taskAddedMessage(task,tasks.size());
        }
    }
}
