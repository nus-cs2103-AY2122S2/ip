package main.commands;

import main.DukeException;
import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Task;

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
