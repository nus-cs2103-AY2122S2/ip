package duke.command;

import duke.datetime.DateTable;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class ShowListCommand extends duke.command.Command {

    @Override
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) {
        ArrayList<Task> storingList = taskList.showStoringList();
        ui.showListTask(storingList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
