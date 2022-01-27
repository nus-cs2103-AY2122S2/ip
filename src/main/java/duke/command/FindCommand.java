package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static final boolean IS_EXIT = false;
    private String searchDescription;

    public FindCommand(String searchDescription) {
        super(IS_EXIT);
        this.searchDescription = searchDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = tasks.findTasks(searchDescription);
        String message = "Charizard found some stuff from the burning list:\n";
        for (int i = 0; i < matchedTasks.size(); i++) {
            message += String.format("%d. %s", i + 1, matchedTasks.get(i));
            if (i < matchedTasks.size() - 1) {
                message += "\n";
            }
        }
        ui.appendMessage(message);
    }
}
