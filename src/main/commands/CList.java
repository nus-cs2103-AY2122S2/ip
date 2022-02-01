package main.commands;

import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Task;

public class CList extends Command {
    public CList() {
        super(CommandType.LIST);
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) {
        ui.respondList(taskList);
    }
}
