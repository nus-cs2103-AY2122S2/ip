package spark.parser.commands.commandtypes;

import spark.exceptions.SparkException;
import spark.parser.params.AddTodoParams;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.Ui;

public class AddToDoCommand extends Command {
    String title;

    public AddToDoCommand(AddTodoParams params) {
        this.title = params.getTitle();
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


