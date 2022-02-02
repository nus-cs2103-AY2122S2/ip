package main.duke.commands;

import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;

public class CBye extends Command{

    public CBye() {
        super(CommandType.BYE);
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) {
        Command.exitDuke();
        ui.respondBye();
    }
}
