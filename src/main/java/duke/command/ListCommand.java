package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.Ui;

import duke.Storage;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST = "Here are the tasks in your list:";

    @Override
    public void execute(List<Task> tasks, Ui ui) throws DukeException {
        String list = MESSAGE_LIST + "\n     ";
        if (tasks.size() == 0) {
            list += "~~List is currently empty~~";
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task thisTask = tasks.get(i);
            list += (i + 1) + ". " + "[" + thisTask.getType() + "]"
                    + "[" + thisTask.getStatusIcon() + "] " + thisTask;
            if (i != tasks.size() - 1) {
                list += "\n     ";
            }
        }
        ui.printContent(list);
        Storage.saveToFile(tasks);
    }
}
