package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command that adds a Todo to the user's task list.
 */
public class TodoCommand extends Command {
    /**
     * Instantiates a new Todo command.
     *
     * @param input The input keyed in by the user.
     */
    public TodoCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        String response;
        try {
            response = ui.createTaskResponse(taskList.addToDo(input), taskList);
            tasksStorage.saveChanges(taskList);
            return response;
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        }
    }
}
