import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND = "todo";

    private final String DESCRIPTION;

    public TodoCommand(String description) {
        this.DESCRIPTION = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.add(new TodoTask(DESCRIPTION));
            ui.showAddSuccess(tasks.taskStatus(tasks.size() - 1), tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
