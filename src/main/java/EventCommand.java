import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {

    public static final String COMMAND = "event";

    private final String DESCRIPTION;
    private final LocalDateTime DATE_TIME;

    public EventCommand(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.add(new EventTask(DESCRIPTION, DATE_TIME));
            ui.showAddSuccess(tasks.taskStatus(tasks.size() - 1), tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
