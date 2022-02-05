package duke.chatbot.command;

import duke.data.TaskList;

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
     * @return ArrayList containing string description of all tasks.
     */
    @Override
    public ArrayList<String> execute() {
        ArrayList<String> response = new ArrayList<>();
        TaskList taskList = this.getTaskList();
        if (taskList.getSize() == 0) {
            response.add("You have no tasks currently!");
        } else {
            ArrayList<String> taskDescriptions = taskList.getTaskDescriptions();
            // Prepend each list item with its numbering inside list
            for (int i = 0; i < taskDescriptions.size(); i++) {
                String description = taskDescriptions.get(i);
                response.add(String.format("%d.%s", i + 1, description));
            }
        }

        return response;
    }
}
