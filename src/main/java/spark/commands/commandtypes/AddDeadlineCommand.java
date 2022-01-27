package spark.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

public class AddDeadlineCommand extends Command {
    private String title;
    private String by;

    public AddDeadlineCommand(String title, String by) {
        this.title = title;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addDeadline(title, by);
            storage.writeTasksFile(tasks.encodeTasks());
            ui.printMessageWithDivider(getAddTaskSuccessMessage(tasks));
        } catch (SparkException e) {
            ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getAddTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've added this task:\n   %s", tasks.getLastAddedTask());
    }
}
