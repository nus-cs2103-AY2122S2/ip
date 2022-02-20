package duke.commands;

import duke.Contact;
import duke.DukeContact;
import duke.DukeUi;

public class DeleteContactsCommand extends Commands {

    private int index;

    public DeleteContactsCommand(DukeContact contact, String[] userInput, DukeUi ui) {
        super(contact, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (NumberFormatException | IndexOutOfBoundsException ex1) {
            return this.getUi().printInvalidArgumentError();
        }
    }

    @Override
    public void validate() {
        this.index = Integer.parseInt(this.getUserInput()[1]) - 1;
        if (this.index < 0 || this.index > this.getContact().getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String execute() {
        StringBuilder description = new StringBuilder();
        Contact tempContact = this.getContact().deleteContact(this.index);
        description.append(tempContact.getContact());
        return "_______________________________________________________\n"
                + "Goodbye for now:\n"
                + "    " + description
                + "Now you have " + this.getContact().getSize() + " contacts in our records.\n"
                + "_______________________________________________________\n";
    }
}
