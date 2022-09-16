package chatbot.command;

import chatbot.list.ContactList;
import chatbot.list.TaskList;
import chatbot.util.Storage;
import chatbot.util.Ui;

/**
 * Represents a command that displays a guide containing valid commands for the user.
 */
public class GuideCommand extends Command {
    /**
     * Instantiates a new Guide command.
     */
    public GuideCommand() {
        super(new String[0]);
    }

    @Override
    public String execute(TaskList taskList, ContactList contactList, Storage tasksStorage,
                          Storage contactsStorage, Ui ui) {
        return ui.printGuide();
    }
}
