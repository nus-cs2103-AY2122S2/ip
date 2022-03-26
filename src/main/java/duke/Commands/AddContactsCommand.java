package duke.commands;

import duke.Contact;
import duke.DukeContact;
import duke.DukeUi;

public class AddContactsCommand extends Commands {

    private int contactNumber;

    public AddContactsCommand(DukeContact contact, String[] userInput, DukeUi ui) {
        super(contact, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (NumberFormatException ex1) {
            return this.getUi().printInvalidArgumentError();
        }
    }

    @Override
    public void validate() {
        this.contactNumber = Integer.parseInt(this.getUserInput()[2]);
    }

    @Override
    public String execute() {
        Contact tempContact = this.getContact().addContact(this.getUserInput()[1], this.contactNumber);
        return "_______________________________________________________\n"
                + "Welcoming a new friend:\n"
                + "    " + tempContact.getContact() + "\n"
                + "Currently you have " + this.getContact().getSize() + " contacts in our records.\n"
                + "_______________________________________________________\n";
    }
}
