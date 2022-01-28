package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByDateCommand extends Command{
    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        ArrayList tasks = new ArrayList(taskManager.getTaskList());
        Collections.sort(tasks,new TaskByDateComparator());

        ui.showList(new TaskManager(tasks));
        return true;
    }
}

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

