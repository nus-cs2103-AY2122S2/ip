package chatbot.command;

import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to find tasks in the user's task list which match to the given keyword.
 */
public class FindCommand extends Command {
    /**
     * Instantiates a new Find command.
     *
     * @param input The input keyed in by the user.
     */
    public FindCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        return taskList.getTasksByKeyword(getInput());
    }
}
