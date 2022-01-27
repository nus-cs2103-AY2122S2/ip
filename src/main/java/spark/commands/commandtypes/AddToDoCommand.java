package spark.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;
import spark.tasks.tasktypes.Task;

public class AddToDoCommand extends Command {
    String title;

    public AddToDoCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addToDo(title);
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


