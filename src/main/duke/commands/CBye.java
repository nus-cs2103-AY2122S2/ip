package main.duke.commands;

import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;

public class CBye extends Command{

    public CBye() {
        super(CommandType.BYE);
    }

    @Override
    public String runCommand(Ui ui, TaskList taskList) {
        Command.exitDuke();
        return ui.respondBye();
    }
}
