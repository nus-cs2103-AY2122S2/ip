package main.commands;

import main.DukeException;
import main.TaskList;
import main.Ui;
import main.enums.CommandType;
import main.tasks.Task;

public class CDelete extends Command{
    protected int deleteIndex;

    public CDelete(int deleteIndex) {
        super(CommandType.DELETE);
        this.deleteIndex = deleteIndex;
    }

    public int getDeleteIndex() {
        return this.deleteIndex;
    }

    @Override
    public void runCommand(Ui ui, TaskList taskList) throws DukeException {
        try {
            Task deleteTask = taskList.getTask(this.getDeleteIndex());
            taskList.deleteTask(this.getDeleteIndex());
            ui.respondDeleteTask(deleteTask, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please check that you have entered the correct index.");
        }
    }
}
