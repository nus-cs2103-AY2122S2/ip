package ui.command;

import data.TaskList;

import java.util.ArrayList;

/**
 * Command which prints out a list of item
 * stored by user.
 */
class ShowTaskListCommand extends TaskListCommand {

    public ShowTaskListCommand(String name, String args, TaskList taskList) {
        super(name, args, taskList);
    }

    /**
     * Displays task stored by user in a list
     * format with numbering.
     *
     * @return False.
     */
    @Override
    public boolean execute() {
        TaskList taskList = this.getTaskList();
        ArrayList<String> taskDescriptions = taskList.getTaskDescriptions();
        ArrayList<String> response = new ArrayList<>();
        // Prepend each list item with its numbering inside list
        for (int i = 0; i < taskDescriptions.size(); i++) {
            String description = taskDescriptions.get(i);
            response.add(String.format("%d.%s", i + 1, description));
        }
        Command.styledPrint(response);
        return false;
    }
}
