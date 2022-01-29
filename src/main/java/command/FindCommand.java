package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    /**
     * Output Tasks that contain a certain word/phrase in their descriptions.
     *
     * @param ui Ui for outputting message.
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        TaskList parsedTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(this.searchPhrase)) {
                parsedTaskList.add(taskList.get(i));
            }
        }
        if (parsedTaskList.size() == 0) {
            ui.outputMessage("Couldn't find any relevant tasks!");
        } else {
            ui.outputMessage("Here are the matching tasks in your list: \n"
                    + parsedTaskList);
        }
    }
}
