package main.commands;

import main.TaskList;
import main.Ui;
import main.enums.CommandType;

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
