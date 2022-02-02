package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Task;

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
