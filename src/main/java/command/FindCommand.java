package command;

import storage.Storage;
import task.TaskList;

public class FindCommand extends Command {
    private final String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    /**
     * Output Tasks that contain a certain word/phrase in their descriptions.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        TaskList parsedTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(this.searchPhrase)) {
                parsedTaskList.add(taskList.get(i));
            }
        }
        if (parsedTaskList.size() == 0) {
            return "Couldn't find any relevant tasks!";
        } else {
            return "Here are the matching tasks in your list: \n"
                    + parsedTaskList;
        }
    }
}
