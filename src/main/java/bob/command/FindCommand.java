package bob.command;

import bob.Storage;
import bob.Task.Task;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

public class FindCommand extends Command {
    public String search;
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        boolean isResultEmpty = true;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.toString().contains(search)) {
                if (isResultEmpty) {
                    ui.searchHasResults();
                }
                isResultEmpty = false;
                ui.say(String.format("\t %o . %s", i + 1, currentTask.printStatus()));
            }
        }
        if (isResultEmpty) {
            ui.noSearchResults();
        } else {
            ui.postListFace();
        }
    }
}
