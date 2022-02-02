package main.duke.commands;

import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;

public class CList extends Command {
    public CList() {
        super(CommandType.LIST);
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) {
        ui.respondList(taskList);
    }
}
