package echo.command;

import echo.storage.Storage;
import echo.task.DeadlineTask;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.main.EchoException;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    public static final String COMMAND = "deadline";

    private final String DESCRIPTION;
    private final LocalDateTime DATE_TIME;

    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.add(new DeadlineTask(DESCRIPTION, DATE_TIME));
            ui.showAddSuccess(tasks.taskStatus(tasks.size() - 1), tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
