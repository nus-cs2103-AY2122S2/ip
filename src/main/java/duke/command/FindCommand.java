package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.task.TaskList;

import java.util.Locale;

public class FindCommand extends Command {

    private String searchInput;

    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

    public void execute(TaskList taskList, Storage storage) {
        StringBuffer result = new StringBuffer("Here are the matching task(s) in your list:\n");
        int count = 1;
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            if (taskList.getTask(i).getDescription().toLowerCase().contains(this.searchInput)) {
                result.append(count).append(".").append(taskList.getTask(i).toString()).append("\n");
                count++;
            }
        }
        if (count == 1) {
            Ui.print(Ui.MSG_NOMATCH);
        } else {
            Ui.print(result.toString().trim());
        }
    }
}
