package duke.Tasks;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * TaskList is a class that deals with the management and storage of tasks
 */
public class TaskList {
    private ArrayList<Task> arrayList;

    /**
     * Constructor for the TaskList class
     * @param arrayList An array list containing the tasks to be stored
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public void add(Task task) {
        arrayList.add(task);
    }

    public Task delete(int index) {
        return arrayList.remove(index);
    }

    public Task get(int index) {
        return arrayList.get(index);
    }

    public int getSize() {
        return arrayList.size();
    }

    public static TaskList sort(TaskList taskList) {
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                LocalTime o1LocalTime;
                if (o1 instanceof Deadline) {
                    o1LocalTime = ((Deadline) o1).deadline.toLocalTime();
                } else {
                    o1LocalTime = ((Event) o1).getEventDate().toLocalTime();
                }

                LocalTime o2LocalTime;
                if (o2 instanceof Deadline) {
                    o2LocalTime = ((Deadline) o2).deadline.toLocalTime();
                } else {
                    o2LocalTime = ((Event) o2).getEventDate().toLocalTime();
                }

                if (o1LocalTime.isAfter(o2LocalTime)) {
                    return 1;
                } else if (o1LocalTime.isBefore(o2LocalTime)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        taskList.arrayList.sort(comparator);
        return taskList;
    }

    public static TaskList toTaskList(Task[] arr) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < arr.length; i++) {
            taskList.add(arr[i]);
        }
        return taskList;
    }

}
