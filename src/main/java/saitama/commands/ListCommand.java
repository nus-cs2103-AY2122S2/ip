package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;

/**
 * A Command object that lists the current tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("OK...");
        if (taskList.numOfTasks() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
