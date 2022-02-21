package mytasks;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList keep tracks of the list of tasks, creates and mark tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor of TaskList keep tracks of the list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Load the task list from a previous instance.
     * @param strList list of tasks that were previously saved.
     */
    public void loadList(List<String> strList) {
        for (String task : strList) {
            String[] tempList = task.split("\\|");
            Task holder;
            switch (tempList[0]) {
            case "T":
                holder = new Todo(tempList[2]);
                break;
            case "D":
                holder = new Deadline(tempList[2], tempList[3]);
                break;
            case "E":
                holder = new Event(tempList[2], tempList[3]);
                break;
            default:
                return;
            }
            if (tempList[1].equals("1")) {
                holder.markAsDone();
            }
            list.add(holder);
        }
    }

    public List<Task> getList() {
        return list;
    }
}
