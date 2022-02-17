package commands;

import tasklist.TaskList;

import tasklist.TaskListException;
import ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            ui.showFindTasks(taskList.find(this.keyword));
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
