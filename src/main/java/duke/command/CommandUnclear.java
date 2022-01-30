package duke.command;

import duke.Ui;

public class CommandUnclear extends Command {
    @Override
    public void execute() {
        Ui.wrapPrint("I'm sorry. I don't understand what that means :d");
    }
}
