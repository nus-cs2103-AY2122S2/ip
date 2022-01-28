package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByNameCommand extends Command{
    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        ArrayList tasks = new ArrayList(taskManager.getTaskList());
        Collections.sort(tasks,new TaskByNameComparator());

        ui.showList(new TaskManager(tasks));
        return true;
    }
}

class TaskByNameComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2){
        return t1.getTaskName().compareTo(t2.getTaskName());
    }
}
