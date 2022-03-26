package duke.commands;

import duke.DukeHistory;
import duke.DukeUi;

public class UpdateCommand extends Commands {

    public UpdateCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        validate();
        return execute();
    }

    @Override
    public void validate() {}

    @Override
    public String execute() {
        return "_______________________________________________________\n"
                + "Duke.txt updated successfully!\n"
                + "_______________________________________________________\n";
    }
}
