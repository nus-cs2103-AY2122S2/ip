package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

import java.io.IOException;

public class ListCommand extends Command {

    public static final String COMMAND = "list";

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showEmptyList();
        } else {
            // Task list is not empty. Prints all tasks.
            StringBuilder listDescription = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String taskStatus = tasks.taskStatus(i) + ("\n");
                taskStatus = Ui.addPrefix(taskStatus);
                listDescription.append(taskStatus);
            }
            listDescription.setLength(listDescription.length() - 1);
            ui.showList(listDescription.toString());
        }
    }
}
