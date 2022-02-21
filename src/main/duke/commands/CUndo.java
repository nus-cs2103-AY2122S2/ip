package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;

public class CUndo extends Command {

    public CUndo() {
        super(CommandType.UNDO);
    }

    @Override
    public String runCommand(Ui ui, TaskList taskList) throws DukeException {
        taskList.undo();
        return ui.respondUndo(taskList);
    }
}
