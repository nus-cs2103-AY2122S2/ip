package duke;

import java.util.Comparator;

public class TaskNameComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getTaskName().compareToIgnoreCase(o2.getTaskName());
    }
}
