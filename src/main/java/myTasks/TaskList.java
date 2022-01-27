package myTasks;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList keep tracks of the list of tasks, creates and mark tasks.
 */
public class TaskList {
    public List<Task> list= new ArrayList<>();

    public TaskList(List<String> strList) {
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
                default:
                    holder = new Event(tempList[2], tempList[3]);
                    break;
            }
            if (tempList[1].equals("1")) {
                holder.markAsDone();
            }
            list.add(holder);
        }
    }

    public TaskList() {}
}
