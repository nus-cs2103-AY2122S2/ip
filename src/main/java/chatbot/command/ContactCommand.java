package chatbot.command;

import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command to add a Contact the user's contact list.
 */
public class ContactCommand extends Command {
    /**
     * Instantiates a new Contact command.
     *
     * @param input the input
     */
    public ContactCommand(String[] input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        String[] input = getInput();
        String response;
        try {
            response = ui.createContactResponse(contactList.add(input), contactList);
            contactsStorage.saveChanges(contactList);
            return response;
        } catch (ChatBotException e) {
            return ui.error(e.getMessage());
        }
    }
}
