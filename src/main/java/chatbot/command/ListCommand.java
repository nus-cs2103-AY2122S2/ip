package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command that displays the user's task list or contact list.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new List command.
     *
     * @param input The input keyed in by the user.
     */
    public ListCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        try {
            return list(getInput(), taskList, contactList);
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        }
    }

    private String list(String[] input, TaskList taskList, ContactList contactList) throws ChatBotException {
        if (input.length == 1) {
            throw new ChatBotException("You need to choose whether you want to list tasks or contacts traveller!");
        }

        switch(input[1]) {
        case "tasks":
            return taskList.summary();
        case "contacts":
            return contactList.summary();
        default:
            throw new ChatBotException();
        }
    }
}
