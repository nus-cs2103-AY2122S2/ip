package main.commands;

import main.DukeException;
import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Task;

public class CUnmark extends Command{
    protected int unmarkIndex;

    public CUnmark(int unmarkIndex) {
        super(CommandType.UNMARK);
        this.unmarkIndex = unmarkIndex;
    }

    public int getUnmarkIndex() {
        return this.unmarkIndex;
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) throws DukeException {
        try {
            Task unmarkTask = taskList.getTask(this.getUnmarkIndex());
            unmarkTask.setIsDone(false);
            ui.respondUnmark(unmarkTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}
