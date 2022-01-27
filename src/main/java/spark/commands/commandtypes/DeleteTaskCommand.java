package spark.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

public class DeleteTaskCommand extends Command {
    int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(index);
            storage.writeTasksFile(tasks.encodeTasks());
        } catch (SparkException e) {
            ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getDeleteTaskSuccessMessage(TaskList tasks) {
        return String.format("Okay! I've removed this task:\n   %s", tasks.getLastDeletedTask());
    }
}
