package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;
import main.duke.tasks.Task;

import java.util.ArrayList;

public class CFind extends Command{
    protected String findString;

    public CFind(String findString) {
        super(CommandType.FIND);
        this.findString = findString;
    }

    public String getFindString() { return this.findString; }

    @Override
    public void runCommand(Ui ui, TaskList taskList) throws DukeException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getTasksCount(); i++) {
            Task curTask = taskList.getTask(i);
            if (curTask.getDescription().contains(this.getFindString())) {
                foundTasks.add(curTask);
            }
        }
        ui.respondFindTask(foundTasks);
    }
}
