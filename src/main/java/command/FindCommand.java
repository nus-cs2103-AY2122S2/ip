package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException { //throw exception if necessary
        TaskList parsedTaskList = new TaskList();
        for (int i=0; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(this.searchPhrase)) {
                parsedTaskList.add(taskList.get(i));
            }
        }
        if (parsedTaskList.size() == 0) {
            ui.outputMessage("Couldn't find any relevant tasks!");
        } else {
            ui.outputMessage("Here are the matching tasks in your list: \n" +
                    parsedTaskList);
        }
    }
}
