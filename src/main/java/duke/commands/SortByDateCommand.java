package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a command to display the current tasks in TaskManager, sorted by Date.
 */
public class SortByDateCommand extends Command{

    /**
     * Executes and displays the given TaskManager to the Ui, ordered by their date.
     *
     * @param storage Not used.
     * @param ui The Ui to display the task list to.
     * @param taskManager The Task Manager that contains the list of task.
     * @return true after the list is displayed.
     * @see TaskByDateComparator
     */
    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        ArrayList tasks = new ArrayList(taskManager.getTaskList());
        Collections.sort(tasks,new TaskByDateComparator());

        ui.showList(new TaskManager(tasks));
        return true;
    }
}

/**
 * Represents a comparator to compare two Tasks, to impose a total ordering on a collection of Tasks,
 * ordered by their dates. If the Task object does not have a date, or the date is not of a LocalDateTime
 * object, it will have a lower order as compared to one that does.
 *
 * @see Task
 * @see java.time.LocalDateTime
 * @see Comparator
 */
class TaskByDateComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        if (t1.getDateObj() != null && t2.getDateObj() != null) {
            return t1.getDateObj().compareTo(t2.getDateObj());
        } else if (t1.getDateObj() == null && t2.getDateObj() == null){
            return t1.getDate().compareTo(t2.getDate());
        } else {
            if (t1.getDateObj() == null){
                return 1;
            } else {
                return -1;
            }
        }
    }
}

