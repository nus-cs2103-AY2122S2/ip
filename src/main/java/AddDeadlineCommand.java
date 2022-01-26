import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime dueBy;

    public AddDeadlineCommand(String title, LocalDateTime dueBy) {
        super(IS_EXIT);
        this.title = title;
        this.dueBy = dueBy;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(title, dueBy);
        tasks.addTask(task);
        ui.showExecutionMessage(Messages.MESSAGE_ADD_DEADLINE, task.toString(), tasks.getSize());

    }
}
