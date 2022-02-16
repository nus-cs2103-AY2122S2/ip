package bro.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.tasks.Task;

/**
 * Represents a command to display the current tasks in TaskManager, sorted by name.
 */
public class SortByNameCommand extends Command {

    /**
     * Executes and displays the given TaskManager to the Ui, ordered by their names.
     *
     * @param storage Not used.
     * @param ui The Ui to display the task list to.
     * @param taskManager The Task Manager that contains the list of task.
     * @return true after the list is displayed.
     * @see TaskByNameComparator
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        ArrayList tasks = taskManager.getTaskList();
        Collections.sort(tasks, new TaskByNameComparator());
        save(storage, ui, taskManager);

        Command list = new ListCommand();
        list.execute(storage, ui, taskManager);
        this.response = list.getResponse();
        return true;
    }
}
/**
 * Represents a comparator to compare two Tasks, to impose a total ordering on a collection of Tasks,
 * ordered by their names lexicographically.
 *
 * @see Task
 * @see Comparator
 */
class TaskByNameComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getTaskName().compareTo(t2.getTaskName());
    }
}
