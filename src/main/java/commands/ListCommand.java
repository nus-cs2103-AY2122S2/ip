package commands;

import ui.Ui;

import tasklist.TaskList;
import tasklist.TaskListException;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    public ListCommand() {}

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            ui.showList(taskList.get());
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
