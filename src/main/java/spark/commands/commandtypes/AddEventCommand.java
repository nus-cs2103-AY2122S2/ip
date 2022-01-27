package spark.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

public class AddEventCommand extends Command {
    private String title;
    private String at;

    public AddEventCommand(String title, String at) {
        this.title = title;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addEvent(title, at);
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
