package duke.command;

import java.util.ArrayList;
import java.util.Comparator;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a List command to display all the list
 */
public class SortCommand extends Command {
    private final String sortBy;

    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Sorts the string out put of list.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        TaskList sortedTaskList;
        switch (sortBy) {
        case "task":
            taskArrayList.sort(Comparator.comparing(Task::getTask));
            break;
        case "mark":
            taskArrayList.sort(Comparator.comparing(Task::getDoneStatus));
            break;
        case "type":
            taskArrayList.sort(Comparator.comparing(o -> o.getClass().getName()));
            break;
        case "priority":
            taskArrayList.sort((o1, o2) -> {
                if (o1.getPriorityText().equals("NORM") && o2.getPriorityText().equals("HIGH")) {
                    return 1;
                } else if (o1.getPriorityText().equals("LOW ") && o2.getPriorityText().equals("HIGH")) {
                    return 1;
                } else if (o1.getPriorityText().equals("LOW ") && o2.getPriorityText().equals("NORM")) {
                    return 1;
                } else if (o1.getPriorityText().equals("NORM") && o2.getPriorityText().equals("LOW ")) {
                    return -1;
                } else if (o1.getPriorityText().equals("HIGH") && o2.getPriorityText().equals("NORM")) {
                    return -1;
                } else if (o1.getPriorityText().equals("HIGH") && o2.getPriorityText().equals("LOW ")) {
                    return -1;
                } else {
                    return 0;
                }
            });
            break;
        case "date":
            taskArrayList.sort((o1, o2) -> {
                if (o1.getDate() == null) {
                    return 1;
                } else if (o2.getDate() == null) {
                    return -1;
                }
                return o1.getDate().compareTo(o2.getDate());
            });
            break;
        default:
            assert false : "Unknown sort by";
        }
        sortedTaskList = new TaskList(taskArrayList);
        ui.displayTaskList(sortedTaskList, "Here are the tasks sorted by " + sortBy + "\n");
        storage.saveTaskList(sortedTaskList);
    }
}
