package duke.command;

import duke.ContactList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Delete contact command. Deletes a contact from the contact list.
 */
public class DeleteContactCommand extends Command {

    private int contactNumber;

    /**
     * Constructs delete command.
     */
    public DeleteContactCommand(int contactNumber) {
        super();
        this.contactNumber = contactNumber;
    }

    /**
     * Deletes the contact with the specified id.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of all contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        try {
            contacts.delete(contactNumber);
            storage.save(contacts);
            return ui.showContactDeleted();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
