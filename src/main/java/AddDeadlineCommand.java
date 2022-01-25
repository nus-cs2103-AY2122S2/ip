import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String description;
    private LocalDateTime deadline;

    public AddDeadlineCommand(String description, LocalDateTime deadline) {
        super(IS_EXIT);
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.add(newDeadline);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                Ui.ADD_MESSAGE, newDeadline, tasks.getSize());
        ui.appendMessage(message);
    }
}
