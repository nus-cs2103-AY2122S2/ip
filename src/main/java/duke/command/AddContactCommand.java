package duke.command;

import duke.Contact;
import duke.ContactList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Adds a contact to the contact list.
 */
public class AddContactCommand extends Command {

    private Contact contact;

    /**
     * Constructs AddCommand.
     *
     * @param contact Contact to add to the list.
     */
    public AddContactCommand(Contact contact) {
        super();
        this.contact = contact;
    }

    /**
     * Adds the specified task to the list.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        try {
            contacts.add(contact);
            storage.save(contacts);
            return ui.showContactAdded(contact);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
