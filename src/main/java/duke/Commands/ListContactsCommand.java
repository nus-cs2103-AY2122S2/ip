package duke.commands;

import duke.DukeContact;
import duke.DukeUi;

public class ListContactsCommand extends Commands {

    public ListContactsCommand(DukeContact contact, String[] userInput, DukeUi ui) {
        super(contact, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        validate();
        return execute();
    }

    @Override
    public void validate() {

    }

    @Override
    public String execute() {
        String border = "_______________________________________________________\n";
        return border
                + "These are your Contacts in our records:\n"
                + this.getContact().printAll() + border;
    }
}
