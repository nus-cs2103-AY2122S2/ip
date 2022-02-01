package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Task;

public class CMark extends Command{
    protected int markIndex;

    public CMark(int markIndex) {
        super(CommandType.MARK);
        this.markIndex = markIndex;
    }

    public int getMarkIndex() {
        return this.markIndex;
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) throws DukeException {
        try {
            Task markTask = taskList.getTask(this.getMarkIndex());
            markTask.setIsDone(true);
            ui.respondMark(markTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}
